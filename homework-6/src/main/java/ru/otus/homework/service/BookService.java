package ru.otus.homework.service;

import org.springframework.validation.annotation.Validated;
import ru.otus.homework.model.Author;
import ru.otus.homework.model.Book;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
public interface BookService {

    Book getById(@NotNull Long id);

    List<Book> getAll();

    void create(@Valid Book book);

    void update(@Valid Book book);

    void deleteById(@NotNull Long id);
}
