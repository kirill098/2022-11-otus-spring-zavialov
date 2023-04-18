package ru.otus.homework.dao;

import ru.otus.homework.model.Genre;

import java.util.Optional;

public interface GenreDao {

    Optional<Genre> getById(Long id);

    Genre create(Genre genre);

    int update(Genre genre);

    void deleteById(Long id);
}
