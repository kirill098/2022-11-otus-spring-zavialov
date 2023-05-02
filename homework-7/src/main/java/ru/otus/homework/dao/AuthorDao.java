package ru.otus.homework.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.model.Author;

public interface AuthorDao extends JpaRepository<Author, Long> {
}
