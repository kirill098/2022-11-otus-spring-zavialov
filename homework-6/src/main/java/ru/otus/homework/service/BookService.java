package ru.otus.homework.service;

import ru.otus.homework.model.Book;

import java.util.List;

public interface BookService {

    Book getById(Long id);

    List<Book> getAll();

    Book create(Book book);

    Book update(Book book);

    void deleteById(Long id);
}
