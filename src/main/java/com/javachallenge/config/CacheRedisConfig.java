package com.javachallenge.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.CacheConfig;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.javachallenge.utils.Constants.*;

@Configuration
public class CacheRedisConfig {

    @Bean
    public CacheManager cacheManager(RedissonClient redissonClient) {
        Map<String, CacheConfig> cacheConfigMap = new HashMap<>();
        cacheConfigMap.put(POINT_OF_SALE_LIST, new CacheConfig(60000, 0));
        cacheConfigMap.put(POINT_OF_SALE, new CacheConfig(60000, 0));
        cacheConfigMap.put(GRAPH, new CacheConfig(60000, 0));
        return new RedissonSpringCacheManager(redissonClient, cacheConfigMap);
    }
    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://" + System.getenv("SPRING_REDIS_HOST") + ":" + System.getenv("SPRING_REDIS_PORT"));
        return Redisson.create(config);
    }
}