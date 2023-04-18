package ru.otus.homework.service;

import org.springframework.validation.annotation.Validated;
import ru.otus.homework.model.Genre;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
public interface GenreService {

    Genre getById(@NotNull Long id);

    void create(@Valid Genre genre);

    void update(@Valid Genre genre);

    void deleteById(@NotNull Long id);
}
