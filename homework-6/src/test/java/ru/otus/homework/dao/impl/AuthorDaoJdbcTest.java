package ru.otus.homework.dao.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.homework.model.Author;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Тестирование AuthorDaoJdbc")
@JdbcTest
@Import(AuthorDaoJdbc.class)
class AuthorDaoJdbcTest {

    @Autowired
    private AuthorDaoJdbc jdbc;

    @DisplayName("получение записи по id")
    @Test
    void getById() {
        val actual = jdbc.getById(3L);
        assertEquals(testGetByIdAuthor(), actual);
    }

    @DisplayName("создание записи")
    @Test
    void create() {
        val expected = testCreateAuthor();
        jdbc.create(expected);
        val actual = jdbc.getById(expected.getId());
        assertEquals(expected, actual);
    }

    @DisplayName("обновление записи")
    @Test
    void update() {
        val expected = testUpdateAuthor();
        jdbc.update(expected);
        val actual = jdbc.getById(expected.getId());
        assertEquals(expected, actual);
    }

    @DisplayName("удаление записи")
    @Test
    void deleteById() {
        val author = testDeleteByIdAuthor();
        val authorId = author.getId();
        jdbc.create(author);
        jdbc.deleteById(authorId);
        assertThrows(EmptyResultDataAccessException.class, () -> jdbc.getById(authorId));
    }

    private static Author testCreateAuthor() {
        return new Author(111L, "author_name_1");
    }

    private static Author testUpdateAuthor() {
        return new Author(2L, "author_name_1");
    }

    private static Author testGetByIdAuthor() {
        return new Author(3L, "author_name_3");
    }

    private static Author testDeleteByIdAuthor() {
        return new Author(112L, "author_name_1");
    }
}