package ru.otus.homework.dao;

import ru.otus.homework.model.Author;

public interface AuthorDao {

    Author getById(Long id);

    void create(Author author);

    void update(Author author);

    void deleteById(Long id);
}
