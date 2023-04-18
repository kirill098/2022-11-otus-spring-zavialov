package ru.otus.homework.dao.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.homework.model.Genre;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Тестирование GenreDaoJdbc")
@JdbcTest
@Import(GenreDaoJdbc.class)
class GenreDaoJdbcTest {

    private static final String TEST_TITLE = "genre_title_3";
    private static final String TEST_GET_BY_ID_TITLE = "genre_title_3";
    private static final long TEST_CREATE_GENRE_ID = 111;

    private static final long TEST_DELETE_BY_ID_GENRE_ID = 112;
    private static final long TEST_UPDATE_GENRE_ID = 2;
    private static final long TEST_GET_BY_ID_GENRE_ID = 3;

    @Autowired
    private GenreDaoJdbc jdbc;

    @DisplayName("получение записи по id")
    @Test
    void getById() {
        val actual = jdbc.getById(TEST_GET_BY_ID_GENRE_ID);
        assertEquals(testGetByIdGenre(), actual);
    }

    @DisplayName("создание записи")
    @Test
    void create() {
        val expected = testCreateGenre();
        jdbc.create(expected);
        val actual = jdbc.getById(expected.getId());
        assertEquals(expected, actual);
    }

    @DisplayName("обновление записи")
    @Test
    void update() {
        val expected = testUpdateGenre();
        jdbc.update(expected);
        val actual = jdbc.getById(expected.getId());
        assertEquals(expected, actual);
    }

    @DisplayName("удаление записи")
    @Test
    void deleteById() {
        val genre = testDeleteByIdGenre();
        val authorId = genre.getId();
        jdbc.create(genre);
        jdbc.deleteById(authorId);
        // todo : мое исключение
        assertThrows(EmptyResultDataAccessException.class, () -> jdbc.getById(authorId));
    }

    private static Genre testCreateGenre() {
        return new Genre(TEST_CREATE_GENRE_ID, TEST_TITLE);
    }

    private static Genre testUpdateGenre() {
        return new Genre(TEST_UPDATE_GENRE_ID, TEST_TITLE);
    }

    private static Genre testGetByIdGenre() {
        return new Genre(TEST_GET_BY_ID_GENRE_ID, TEST_GET_BY_ID_TITLE);
    }

    private static Genre testDeleteByIdGenre() {
        return new Genre(TEST_DELETE_BY_ID_GENRE_ID, TEST_TITLE);
    }
}