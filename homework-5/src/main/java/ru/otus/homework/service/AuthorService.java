package ru.otus.homework.service;

import org.springframework.validation.annotation.Validated;
import ru.otus.homework.model.Author;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface AuthorService {

    Author getById(@NotNull Long id);

    void create(@Valid Author author);

    void update(@Valid Author author);

    void deleteById(@NotNull Long id);
}
