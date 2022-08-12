package ch.supsi.gavdat.mongo.blog.redis;

import ch.supsi.gavdat.mongo.blog.dao.exception.NotImplementedException;
import redis.clients.jedis.Jedis;

public class SessionHandler {

    private static final String SESSION_KEY_PREFIX = "blog:session:";
    private static final int SESSION_EXPIRATION = 1800; // seconds

    private Jedis jedis = new Jedis();

    public void setSession(String sessionID, String username){
        // inserire in redis una nuovo valore con:
        //      - chiave: SESSION_KEY_PREFIX+sessionID
        //      - valore: username
        // settare a SESSION_EXPIRATION l'expiration time della chiave appena creata

        throw new NotImplementedException();
    }

    public String getUser(String sessionID) {
        // recuperare il valore della chiave: SESSION_KEY_PREFIX+sessionID
        // e ritornalo

        throw new NotImplementedException();
    }

    public void removeSession(String sessionID) {
        // rimuovere la chiave: SESSION_KEY_PREFIX+sessionID

        throw new NotImplementedException();
    }

    public void updateExpiration(String sessionID) {
        // aggiornare l'expiration time della chiave: SESSION_KEY_PREFIX+sessionID
        // al valore SESSION_EXPIRATION

        throw new NotImplementedException();
    }

}