package com.example.service;

import com.example.repository.AuthorRepository;
import io.micronaut.context.annotation.Primary;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Primary
@Singleton
public class AuthorService {

    @Inject
    AuthorRepository repository;


    public void getAll(){
        repository.findAll();
    }
}
