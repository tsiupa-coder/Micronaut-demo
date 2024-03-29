package com.example.repository;

import com.example.model.Book;
import com.example.model.dto.BookDTO;
import io.micronaut.context.annotation.Executable;
import io.micronaut.data.annotation.Join;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.annotation.EntityGraph;
import io.micronaut.data.jpa.repository.JpaSpecificationExecutor;
import io.micronaut.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    @Executable
    @Join(value = "author", type = Join.Type.FETCH)
    Optional<Book> findByTitle(String title);

    Optional<Book> findByIdAndBooked(Long id, boolean booked);
    @Executable
    @Join(value = "author", type = Join.Type.FETCH)
    List<Book> findByBooked(boolean booked);

    @Join(value = "author", type = Join.Type.FETCH)
    List<Book> list();

    @EntityGraph(attributePaths = {"author", "title"})
    List<Book> findAll();

    Optional<Integer> findPagesById(Long id);

    Long findSumPages();

    List<BookDTO> find();
}