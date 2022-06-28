package com.example.service;

import io.micronaut.context.annotation.Primary;
import jakarta.inject.Singleton;

@Primary
@Singleton
public class GreetingService {

    public String getGreeting(){

        return "Hi, nice to see you here";

    }
}
