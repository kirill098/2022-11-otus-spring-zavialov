package ru.otus.homework.dao.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.homework.model.Book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Тестирование BookDaoJdbc")
@JdbcTest
@Import(BookDaoJdbc.class)
class BookDaoJdbcTest {

    @Autowired
    private BookDaoJdbc jdbc;

    @DisplayName("получение записи по id")
    @Test
    void getById() {
        val expected = testGetByIdBook();
        val actual = jdbc.getById(expected.getId());
        assertEquals(expected, actual);
    }

    @DisplayName("получение всех записей")
    @Test
    void getAll() {
        val actual = jdbc.getAll();
        assertEquals(7, actual.size());
    }

    @DisplayName("создание записи")
    @Test
    void create() {
        val expected = testCreateBook();
        jdbc.create(expected);
        val actual = jdbc.getById(expected.getId());
        assertEquals(expected, actual);
    }

    @DisplayName("обновление записи")
    @Test
    void update() {
        val expected = testUpdateBook();
        jdbc.update(expected);
        val actual = jdbc.getById(expected.getId());
        assertEquals(expected, actual);
    }

    @DisplayName("удаление записи")
    @Test
    void deleteById() {
        val book = testDeleteByIdBook();
        val bookId = book.getId();
        jdbc.create(book);
        jdbc.deleteById(bookId);
        assertThrows(EmptyResultDataAccessException.class, () -> jdbc.getById(bookId));
    }

    private static Book testCreateBook() {
        return new Book(111L, "book_title_1", 1L, 1L);
    }

    private static Book testUpdateBook() {
        return new Book(2L, "MyTitle", 2L, 2L);
    }

    private static Book testGetByIdBook() {
        return new Book(1L, "book_title_1", 1L, 1L);
    }

    private static Book testDeleteByIdBook() {
        return new Book(222L, "book_title_3", 3L, 3L);
    }

}