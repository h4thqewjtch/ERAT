package com.example.calculator.cache;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class Cache {
    private static final Map<String, Double> map = Collections.synchronizedMap(new HashMap<>());

    public Map<String, Double> getMap() {
        return map;
    }

    public String evict() {
        if (map.size() != 0)
            map.clear();
        return "Current Status : All cache entries have been evicted!";
    }

    public void putCache(String key, double value) {
        map.putIfAbsent(key, value);
    }

    public String getCache(String key) {
        return "" + map.get(key);
    }
}
