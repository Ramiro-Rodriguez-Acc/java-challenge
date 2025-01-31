package com.javachallenge.cache;

import org.redisson.api.RBucket;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.NoSuchElementException;

import static com.javachallenge.utils.Constants.*;


@Service
public class CacheService {
    private final RedissonClient redissonClient;

    public CacheService(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    public <T> Map<Integer, T> getMap(String key, Class<T> classType) throws Exception {
        RMapCache<Integer, T> map= redissonClient.getMapCache(key, new TypedJsonJacksonCodec(Integer.class, classType));
        return map.readAllMap();
    }

    public <T> void putMap(String key, Map<Integer, T> value, Class<T> classType) {
        RMapCache<Integer, T> map = redissonClient.getMapCache(key, new TypedJsonJacksonCodec(Integer.class, classType));
        map.putAll(value);

        RBucket<Integer> idBucket = redissonClient.getBucket(key+ID);
        idBucket.set(value.size());
    }

    public <T> void add(T newValue, String key, Class<T> classType) throws Exception {
        RMapCache<Integer, T> map = redissonClient.getMapCache(key, new TypedJsonJacksonCodec(Integer.class, classType));
        RBucket<Integer> idBucket = redissonClient.getBucket(key+ID);
        Integer id = idBucket.get();;
        int newId = id + 1;
        map.put(newId, newValue);
        idBucket.set( newId);
    }

    public <T> void update(int id, T newValue, String key, Class<T> classType) throws Exception {
        RMapCache<Integer, T> map = redissonClient.getMapCache(key, new TypedJsonJacksonCodec(Integer.class, classType));
        map.put(id, newValue);
    }

    public <T> void delete(int id, String key, Class<T> classType) throws Exception {
        RMapCache<Integer, T> map = redissonClient.getMapCache(key, new TypedJsonJacksonCodec(Integer.class, classType));
        if (map.remove(id) == null) {
            throw new NoSuchElementException(ERROR_MESSAGE_PUNTO_DE_VENTA_NO_ENCONTRADO);
        }

    }
}
