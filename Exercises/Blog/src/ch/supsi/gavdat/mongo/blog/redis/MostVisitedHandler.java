package ch.supsi.gavdat.mongo.blog.redis;

import ch.supsi.gavdat.mongo.blog.dao.exception.NotImplementedException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MostVisitedHandler {

    private static final String MOST_VISITED_KEY_PREFIX = "blog:mostvisited:";

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm");

    private Jedis jedis = new Jedis();

    public void visited(String permalink) {
        String key = MOST_VISITED_KEY_PREFIX+getCurrent1MinutePeriodKey();

        // aggiungere al sorted set con chiave: key
        // l'elemento permalink incerementando il valore di 1
        // Hint: utilizzare il metodo zincrby di jedis

        // aggiungere un expiration time al sorted set appena creato di 5000 secondi

        throw new NotImplementedException();
    }

    public List<Post> getLastHourTop10MostVisited() {
        String key = MOST_VISITED_KEY_PREFIX+getCurrent1MinutePeriodKey()+":lasthourtop10";

        // creare un nuovo sorted set unendo tutti i sorted dell'ultima ora
        // utilizzare il metodo getLastHourPeriodKeys() che ritorna una lista di stringhe
        // che rappresentano le chiavi dell'ultima ora

        // aggiungere un expiration time a questo nuovo sorted set di 120 secondi

        // con il metodo zrevrangeWithScores di jedis
        // far calcolare i top 10 post dell'ultima ora
        // ritornare una lista di MostVisitedHandler.Post
        // degli oggetti Post bisogna valorizzare il permalink
        // e il totale delle visite

        throw new NotImplementedException();
    }

    private String getCurrent1MinutePeriodKey() {
        return LocalDateTime.now().format(formatter);
    }

    private List<String> getLastHourPeriodKeys() {
        LocalDateTime now = LocalDateTime.now();
        List<String> result = new ArrayList<>();
        for (int i : IntStream.range(0,60).toArray()){
            result.add(MOST_VISITED_KEY_PREFIX+now.format(formatter));
            now = now.minus(1, ChronoUnit.MINUTES);
        }
        return result;
    }

    public static class Post {
        private String permalink;
        private Double count;

        public Post(String permalink, Double count){
            this.permalink = permalink;
            this.count = count;
        }

        public String getPermalink() {
            return permalink;
        }

        public Integer getCount() {
            return count.intValue();
        }
    }

}

