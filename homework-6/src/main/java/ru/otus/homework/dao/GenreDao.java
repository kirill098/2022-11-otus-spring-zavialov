package ru.otus.homework.dao;

import ru.otus.homework.model.Genre;

import java.util.Optional;

public interface GenreDao {

    Optional<Genre> getById(Long id);

    Genre save(Genre genre);

    void deleteById(Long id);
}
