package ru.otus.homework.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.model.Genre;

public interface GenreDao extends JpaRepository<Genre, Long> {
}
