package com.example.controller.api;

import com.example.model.Author;
import com.example.service.AuthorService;
import io.micronaut.core.version.annotation.Version;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.annotation.Status;
import jakarta.inject.Inject;

import javax.validation.constraints.NotNull;
import java.util.List;

import static com.example.generall.URlConstants.AUTHORS;
import static com.example.generall.URlConstants.ID;
import static com.example.generall.URlConstants.VERSION_1_1;

@Controller(AUTHORS)
public class AuthorController {

    @Inject
    private AuthorService service;

    @Version(VERSION_1_1)
    @Get
    public List<Author> getAll() {
        return service.getAll();
    }

    @Get(ID)
    public Author get(Long id) {
        return service.get(id);
    }

    @Status(HttpStatus.CREATED)
    @Version(VERSION_1_1)
    @Post
    public void create(@Body Author author) {
        service.create(author);
    }

    @Status(HttpStatus.ACCEPTED)
    @Version(VERSION_1_1)
    @Put(ID)
    public void update(@PathVariable @NotNull Long id, @Body @NotNull Author author) {
        service.update(id, author);
    }

    @Status(HttpStatus.NO_CONTENT)
    @Version(VERSION_1_1)
    @Delete(ID)
    public void delete(@PathVariable @NotNull Long id) {
        service.delete(id);
    }
}
