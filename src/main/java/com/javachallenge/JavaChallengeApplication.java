package com.javachallenge;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@EnableCaching
@SpringBootApplication
public class JavaChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaChallengeApplication.class, args);
    }

}
