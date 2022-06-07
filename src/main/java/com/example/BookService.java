package com.example;

import io.micronaut.context.annotation.Primary;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;

@Primary
@Singleton
public class BookService {

    @Inject
    BookRepository repository;

    public void initDataBase(){

        Pen book = new Pen();
        book.setTitle("The Stand");
        book.setPages(1000);
        repository.save(book);
    }

    public List<Pen> findAll(){

        return (List<Pen>) repository.findAll();
    }
}
