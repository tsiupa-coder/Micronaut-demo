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

        Book book = new Book();
        book.setTitle("The Stand");
        book.setPages(1000);
        repository.save(book);
    }

    public List<Book> findAll(){

        return (List<Book>) repository.findAll();
    }
}
