package com.javachallenge.cache;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.javachallenge.utils.Constants.*;


@Service
public class CacheService {
    private final SimpleCache<Integer, String> cache;

    public CacheService(SimpleCache<Integer, String> cache) {
        this.cache = cache;
    }

    public <T> Map<Integer, T> getMap(String key) throws Exception {
        Object value = cache.get(key);
        if (value instanceof Map) {
            return Map.copyOf((Map<Integer, T>) value);
        }
        throw new Exception(ERROR_MESSAGE_ERROR_INTERNO);
    }

    public int getLength(String key) throws Exception {
        Object value = cache.get(key+ID);
        if (value instanceof Integer) {
            return (int) value;
        }
        throw new Exception(ERROR_MESSAGE_ERROR_INTERNO);

    }

    public  void putMap(String key, Map<Integer, Object> value) {
        cache.put(key,value, Map.class);
        cache.put(key+ID, value.size(), Integer.class);
    }

    public <T> void add(T newValue, String key) throws Exception {
        Object value = cache.get(key);
        if (value instanceof Map) {
             Map<Integer, T> map = (HashMap<Integer, T>) value;
             Integer id = (Integer) cache.get(key+ID);
             int newId = id + 1;
             map.put(newId, newValue);
             cache.put(key+ID, newId, Integer.class);
        } else {
            throw new Exception(ERROR_MESSAGE_ERROR_INTERNO);
        }
    }

    public <T> void update(int id, T newValue, String key) throws Exception {
        Object mapObject = cache.get(key);
        if (mapObject instanceof Map) {

            Map<Integer, T> map = (Map<Integer, T>) mapObject;
            map.put(id, newValue);
        } else {
            throw new Exception(ERROR_MESSAGE_ERROR_INTERNO);
        }
    }

    public <T> void delete(int id, String key) throws Exception {
        Object mapObject = cache.get(key);
        if (mapObject instanceof Map) {
            Map<Integer, T> map = (HashMap<Integer, T>) mapObject;
            Optional.ofNullable(map.remove(id))
                    .orElseThrow(() ->new NoSuchElementException(ERROR_MESSAGE_PUNTO_DE_VENTA_NO_ENCONTRADO));

        } else {
            throw new Exception(ERROR_MESSAGE_ERROR_INTERNO);
        }
    }
}
