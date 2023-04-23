package ru.otus.homework.dao;

import ru.otus.homework.model.Author;

import java.util.Optional;

public interface AuthorDao {

    Optional<Author> getById(Long id);

    Author save(Author author);

    void deleteById(Long id);
}
