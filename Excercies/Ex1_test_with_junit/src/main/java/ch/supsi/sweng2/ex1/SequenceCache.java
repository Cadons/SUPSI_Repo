package ch.supsi.sweng2.ex1;

import java.util.HashMap;
import java.util.Map;

public class SequenceCache implements Cache {
    final Map<Integer, Worker> cache = new HashMap<>();

    @Override
    public int length(int v) throws Exception {
        if (cache.get(v) == null)
            cache.put(v,createWorker(v));

        return cache.get(v).sequence();
    }
   public Worker createWorker(int v){
       return new Worker(v);
    }
}
