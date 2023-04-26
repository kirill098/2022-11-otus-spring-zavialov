package ru.otus.homework.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.model.Book;

public interface BookDao extends JpaRepository<Book, Long> {
}
