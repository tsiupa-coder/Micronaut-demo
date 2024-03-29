package com.example.service;

import com.example.exceptions.BookIsInUseException;
import com.example.exceptions.ItemNotFoundException;
import com.example.model.Book;
import com.example.model.dto.BookDTO;
import com.example.repository.BookRepository;
import io.micronaut.context.annotation.Primary;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Primary
@Singleton
public class BookService {

    @Inject
    BookRepository repository;

    @Transactional
    public void initDataBase() {

        Book book = new Book();
        book.setTitle("The Stand");
        book.setPages(1000);
        repository.save(book);
    }

    @Transactional
    public List<BookDTO> findAllDTO() {

        return repository.find();
    }

    @Transactional
    public List<Book> findAll() {
        return repository.list();
    }


    @Transactional
    public Book findByTitle(String title) {
        return repository
                .findByTitle(title)
                .orElseThrow(
                        () -> new ItemNotFoundException(
                                String.format(
                                        "Book with title %S not found, sorry", title
                                )
                        )
                );
    }


    @Transactional
    public Long countBooks() {
        return repository.count();
    }

    @Transactional
    public Book fall() {

        return repository
                .findById(null)
                .orElseThrow((ItemNotFoundException::new));

    }

    @Transactional
    public Long totalPages(List<Long> id) {

        return id.stream()
                .map(this::getPages)
                .collect(Collectors.summarizingLong(Integer::intValue))
                .getCount();
    }

    @Transactional
    public Integer getPages(Long bookId) {
        return repository.findPagesById(bookId).orElse(0);
    }

    @Transactional
    public Long sumPages() {
        return repository.findSumPages();
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

        if (book.isPresent()) {
            Book bookUpdated = book.get();
            bookUpdated.setTitle(title);
            repository.update(bookUpdated);
        }
    }
    @Transactional
    public void booked(Long id) {
        Book book = repository.findByIdAndBooked(id, false).orElseThrow(BookIsInUseException::new);
        book.setBooked(true);
    }

    @Transactional
    public List<Book> booked() {
        return repository.findByBooked(true);
    }
}
