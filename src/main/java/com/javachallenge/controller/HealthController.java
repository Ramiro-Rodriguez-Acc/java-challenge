package com.javachallenge.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @GetMapping("/health") public String health() {
        return "Everything is awesome";
    }

    @GetMapping("/sleep") public String sleep() throws InterruptedException {
        Thread.sleep(10000);
        return "Everything is awesome";
    }

}
