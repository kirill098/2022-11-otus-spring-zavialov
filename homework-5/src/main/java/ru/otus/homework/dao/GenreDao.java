package ru.otus.homework.dao;

import ru.otus.homework.model.Genre;

public interface GenreDao {

    Genre getById(Long id);

    void create(Genre genre);

    void update(Genre genre);

    void deleteById(Long id);
}
