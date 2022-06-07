package com.example;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.context.annotation.Executable;
import io.micronaut.data.annotation.*;
import io.micronaut.data.model.*;
import io.micronaut.data.repository.CrudRepository;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Repository // 
interface BookRepository extends CrudRepository<Pen, Long> {

    @Executable
    Pen find(String title);





}