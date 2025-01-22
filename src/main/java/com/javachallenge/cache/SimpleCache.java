package com.javachallenge.cache;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SimpleCache<K, V> {
    private final Map<String, Object> cache = new HashMap<>();

    public  Object get(String key) {
        return cache.get(key);

    }

    public <T> void put(String key, T value, Class<T> type) {
        cache.put(key, value);
    }

}
