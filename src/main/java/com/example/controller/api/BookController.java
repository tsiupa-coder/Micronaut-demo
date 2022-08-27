package com.example.controller.api;

import com.example.model.Book;
import com.example.service.BookService;
import io.micronaut.core.version.annotation.Version;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.annotation.Status;
import jakarta.inject.Inject;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.generall.URlConstants.BOOKS;
import static com.example.generall.URlConstants.COUNT;
import static com.example.generall.URlConstants.ID;
import static com.example.generall.URlConstants.VERSION_1_1;

@Controller(BOOKS)
public class BookController {

    @Inject
    private BookService service;

    @Get()
    public List<Book> find(@QueryValue @Size(max = 1024, min = 1) Optional<String> title) {
        List<Book> books;
        if (title.isPresent()) {
            books = new ArrayList<>();
            books.add(service.findByTitle(title.get()));
        } else {
            books = service.findAll();
        }
        return books;
    }

    @Version(VERSION_1_1)
    @Get(COUNT)
    public Long count() {
        return service.countBooks();
    }

    @Version(VERSION_1_1)
    @Status(HttpStatus.CREATED)
    @Post
    public void create(Book book) {
        service.create(book);
    }

    @Version(VERSION_1_1)
    @Status(HttpStatus.NO_CONTENT)
    @Delete(ID)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @Version(VERSION_1_1)
    @Status(HttpStatus.ACCEPTED)
    @Put(ID)
    public void update(@PathVariable Long id, @QueryValue @NotBlank String name) {
        service.updateName(id, name);
    }
}