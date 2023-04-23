package ru.otus.homework.dao;

import ru.otus.homework.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    Optional<Book> getById(Long id);

    List<Book> getAll();

    Book save(Book book);

    void deleteById(Long id);
}
