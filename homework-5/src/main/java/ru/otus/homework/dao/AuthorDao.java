package ru.otus.homework.dao;

import ru.otus.homework.model.Author;

public interface AuthorDao {

    Author getById(Long id);

    void create(Author author);

    int update(Author author);

    int deleteById(Long id);
}
