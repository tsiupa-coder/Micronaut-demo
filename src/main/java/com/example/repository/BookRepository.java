package com.example.repository;

import com.example.model.Book;
import io.micronaut.context.annotation.Executable;
import io.micronaut.data.annotation.*;
import io.micronaut.data.repository.CrudRepository;

@Repository // 
interface BookRepository extends CrudRepository<Book, Long> {

    @Executable
    Book find(String title);





}