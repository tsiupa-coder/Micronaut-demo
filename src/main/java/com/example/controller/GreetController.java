package com.example.controller;

import com.example.service.GreetingService;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;
import jakarta.inject.Inject;

@Controller("/greet")
public class GreetController {

    @Inject
    private GreetingService greetingService;

    @Get("/{name}")
    public String greet(String name) {
        return greetingService.getGreeting() + " " + name;
    }

    @Post("/post")
    public String setGreeting(@QueryValue String name) {
        return greetingService.getGreeting() + " " + name;
    }
}