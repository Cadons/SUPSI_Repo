package ch.supsi.gavdat.mongo.blog.redis;

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
        jedis.zincrby(key, 1, permalink);
        jedis.expire(key, 5000);
    }

    public List<Post> getLastHourTop10MostVisited() {
        String key = MOST_VISITED_KEY_PREFIX+getCurrent1MinutePeriodKey()+":lasthour";
        jedis.zunionstore(key, getLastHourPeriodKeys().toArray(new String[]{}));
        jedis.expire(key, 120);
        Set<Tuple> tuples = jedis.zrevrangeWithScores(key, 0, 10);
        return tuples.stream().map(t -> new Post(t.getElement(), t.getScore())).collect(Collectors.toList());
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

