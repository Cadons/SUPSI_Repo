package ch.supsi.gavdat.mongo.blog.dao;

import ch.supsi.gavdat.mongo.blog.dao.exception.NotImplementedException;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoWriteException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.commons.codec.binary.Base64;
import org.bson.Document;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class UserDAO {
    private final MongoCollection<Document> usersCollection;
    private Random random = new SecureRandom();

    public UserDAO(final MongoDatabase blogDatabase) {
        usersCollection = blogDatabase.getCollection("users");
    }

    // validates that username is unique and insert into db
    public boolean addUser(String username, String password, String email) {

        String passwordHash = makePasswordHash(password, Integer.toString(random.nextInt()));

        // TODO DA IMPLEMENTARE
        // Aggiungere l’inserimento di un nuovo utente, usare makePasswordHash
        // verificando che il nome passato come parametro non
        // sia già esistente sulla banca dati (usare l'eccezione com.mongodb.MongoWriteException, che mongo darà se ci sono _id duplicati).
        // ritorna true se tutto avviene con successo
        BasicDBObject query = new BasicDBObject();
        query.put("_id", username);
        if (usersCollection.find(query).first() != null) {
            usersCollection.insertOne(new Document().append("_id", username).append("password", passwordHash).append("email", email));
            return true;
        } else {
            return false;
        }
    }


    public Document validateLogin(String username, String password) {
        // TODO DA IMPLEMENTARE
        // trovare l'utente con il dato username
        // e controllare che la password passata come argomento sia uguale
        // a quella sulla banca dati. Usare il metodo validatePassword
        BasicDBObject query = new BasicDBObject();
        query.put("_id", username);
        FindIterable<Document> result = usersCollection.find(query);

        if (validatePassword(String.valueOf(result.first().get("password")), password))
            return result.first();
        else
            return null;
    }


    private boolean validatePassword(String dbpassword, String webpassword) {
        String salt = dbpassword.split(",")[1];

        if (dbpassword.equals(makePasswordHash(webpassword, salt))) {
            return true;
        }

        return false;
    }


    private String makePasswordHash(String password, String salt) {
        try {
            String saltedAndHashed = password + "," + salt;
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(saltedAndHashed.getBytes());
            byte hashedBytes[] = (new String(digest.digest(), "UTF-8")).getBytes();
            return Base64.encodeBase64String(hashedBytes) + "," + salt;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 is not available", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 unavailable?  Not a chance", e);
        }
    }
}
