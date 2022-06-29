package com.example.controller;

import com.example.model.dto.BookDTO;
import com.example.service.BookService;
import com.example.model.Book;
import io.micronaut.http.HttpMessage;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.QueryValue;
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

    @Get
    public List<BookDTO> books() {
        return service.findAll();
    }

    @Get("/model")
    public List<Book> find(){
        return service.find();
    }
    @Get("/title")
    public Book find(@QueryValue String title){
        return service.findByTitle(title);
    }

    @Post
    public HttpMessage create(Book book) {
        service.create(book);

        return HttpResponse.status(HttpStatus.CREATED);
    }

    @Delete("{id}")
    public HttpMessage delete(@PathVariable Long id){

        service.delete(id);

        return HttpResponse.status(HttpStatus.NO_CONTENT);
    }


    @Put("{id}")
    public HttpMessage update(@PathVariable Long id, @QueryValue String name) {

        service.updateName(id, name);

        return HttpResponse.status(HttpStatus.ACCEPTED);
    }

    @Get("/fall")
    public void fall() {
        service.fall();
    }

    @Get(value = "/test")
    public String test() {

        return "HI";
    }
}