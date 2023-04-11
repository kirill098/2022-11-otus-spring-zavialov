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

    private static final String TEST_NAME = "author_name_1";
    private static final String TEST_GET_BY_ID_NAME = "author_name_3";
    private static final long TEST_CREATE_AUTHOR_ID = 111;

    private static final long TEST_DELETE_BY_ID_AUTHOR_ID = 112;
    private static final long TEST_UPDATE_AUTHOR_ID = 2;
    private static final long TEST_GET_BY_ID_AUTHOR_ID = 3;

    @Autowired
    private AuthorDaoJdbc jdbc;

    @DisplayName("получение записи по id")
    @Test
    void getById() {
        val actual = jdbc.getById(TEST_GET_BY_ID_AUTHOR_ID);
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
        return new Author(TEST_CREATE_AUTHOR_ID, TEST_NAME);
    }

    private static Author testUpdateAuthor() {
        return new Author(TEST_UPDATE_AUTHOR_ID, TEST_NAME);
    }

    private static Author testGetByIdAuthor() {
        return new Author(TEST_GET_BY_ID_AUTHOR_ID, TEST_GET_BY_ID_NAME);
    }

    private static Author testDeleteByIdAuthor() {
        return new Author(TEST_DELETE_BY_ID_AUTHOR_ID, TEST_NAME);
    }
}