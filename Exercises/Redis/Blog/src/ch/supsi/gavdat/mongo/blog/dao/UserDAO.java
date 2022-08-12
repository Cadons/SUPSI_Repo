package ch.supsi.gavdat.mongo.blog.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoWriteException;
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

        Document user = new Document("_id", username).append("password", passwordHash);

        if (email != null && !email.equals("")) {
            user.append("email", email);
        }

        try {
            usersCollection.insertOne(user);
            return true;
        } catch (MongoWriteException e) {
            return false;
        }
    }


    public Document validateLogin(String username, String password) {
        Document user = usersCollection.find(new Document("_id", username)).first();

        if (user == null) {
            return null;
        }

        if(validatePassword(user.get("password").toString(), password)){
        	return user;
        }
        
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
            //BASE64Encoder encoder = new BASE64Encoder();
            byte hashedBytes[] = (new String(digest.digest(), "UTF-8")).getBytes();
            return Base64.encodeBase64String(hashedBytes) + "," + salt;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 is not available", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 unavailable?  Not a chance", e);
        }
    }
}
