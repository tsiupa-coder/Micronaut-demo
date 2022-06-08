package com.example;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import jakarta.inject.Inject;

import java.util.List;

@Controller("/book")
public class BookController {

    @Inject
    private BookService service;

    @Get("/init")
    public void init() {
        service.initDataBase();
    }

    @Get("/books")
    public List<Book> books() {
        return service.findAll();
    }

}