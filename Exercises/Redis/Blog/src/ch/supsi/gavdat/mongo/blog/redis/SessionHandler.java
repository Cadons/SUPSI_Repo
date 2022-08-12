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
        jedis.set(SESSION_KEY_PREFIX+sessionID,username);
        jedis.expire(SESSION_KEY_PREFIX+sessionID,SESSION_EXPIRATION);
    }

    public String getUser(String sessionID) {
        // recuperare il valore della chiave: SESSION_KEY_PREFIX+sessionID
        // e ritornalo

        return jedis.get(SESSION_KEY_PREFIX+sessionID);
    }

    public void removeSession(String sessionID) {
        // rimuovere la chiave: SESSION_KEY_PREFIX+sessionID
        jedis.del(SESSION_KEY_PREFIX+sessionID);
    }

    public void updateExpiration(String sessionID) {
        // aggiornare l'expiration time della chiave: SESSION_KEY_PREFIX+sessionID
        // al valore SESSION_EXPIRATION
        jedis.expire(SESSION_KEY_PREFIX+sessionID,SESSION_EXPIRATION);
    }

}