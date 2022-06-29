package com.example.repository;

import com.example.model.Book;
import io.micronaut.context.annotation.Executable;
import io.micronaut.data.annotation.*;
import io.micronaut.data.jpa.annotation.EntityGraph;
import io.micronaut.data.jpa.repository.JpaSpecificationExecutor;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    @Executable
    Book findByTitle(String title);

    @Join(value = "author", type = Join.Type.FETCH) //
    List<Book> list();

    @EntityGraph(attributePaths = {"author", "title"}) //
    List<Book> findAll();
}