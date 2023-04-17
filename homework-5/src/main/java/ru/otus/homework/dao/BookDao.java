package ru.otus.homework.dao;

import ru.otus.homework.model.Book;

import java.util.List;

public interface BookDao {

    Book getById(Long id);

    List<Book> getAll();

    void create(Book book);

    int update(Book book);

    int deleteById(Long id);
}
