package com.example.service;

import com.example.model.Book;
import com.example.model.dto.BookDTO;
import com.example.repository.BookRepository;
import io.micronaut.context.annotation.Primary;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Primary
@Singleton
public class BookService {

    @Inject
    BookRepository repository;

    @Transactional
    public void initDataBase(){

        Book book = new Book();
        book.setTitle("The Stand");
        book.setPages(1000);
        repository.save(book);
    }

    @Transactional
    public List<BookDTO> findAll(){

        return repository.find();
    }

    @Transactional
    public List<Book> find(){
        return repository.list();
    }


    @Transactional
    public Book findByTitle(String title){

        return repository.findByTitle(title);
    }
    @Transactional
    public Book fall(){

        return repository
                .findById(null)
                .orElseThrow((NoSuchElementException::new));

    }

    @Transactional
    public void create(Book book) {
        repository.save(book);
    }

    @Transactional
    public void delete(Long id) {

        repository.deleteById(id);
    }

   @Transactional
    public void updateName(Long id, String title) {

        Optional<Book> book = repository.findById(id);

        if(book.isPresent()) {
            Book bookUpdated = book.get();
            bookUpdated.setTitle(title);
            repository.update(bookUpdated);
        }
    }
}
