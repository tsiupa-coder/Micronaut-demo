package com.example.service;

import com.example.exceptions.ItemNotFoundException;
import com.example.model.Author;
import com.example.repository.AuthorRepository;
import io.micronaut.context.annotation.Primary;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Primary
@Singleton
public class AuthorService {
    @Inject
    AuthorRepository repository;

    @Transactional
    public List<Author> getAll() {
        return repository.findAll();
    }

    public Author get(Long id) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("Id is null");
        }
        return repository.findById(id).orElseThrow(() -> new ItemNotFoundException(String.format("Author with %s was not found", id)));
    }

    public void create(Author author) {
        if (Objects.isNull(author)) {
            throw new IllegalArgumentException("Entity author is null");
        }
        repository.save(author);
    }

    public void update(Long id, Author author) {
        if (Objects.isNull(author)) {
            throw new IllegalArgumentException("Entity author is null");
        }
        Author DBAuthor = get(id);
        DBAuthor.setName(author.getName());
        repository.update(DBAuthor);
    }

    public void delete(Long id) {
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("Id is null");
        }
        repository.deleteById(id);
    }
}
