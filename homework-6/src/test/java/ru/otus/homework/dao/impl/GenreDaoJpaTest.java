package ru.otus.homework.dao.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.otus.homework.dao.GenreDao;
import ru.otus.homework.model.Book;
import ru.otus.homework.model.Genre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Тестирование GenreDaoJdbc")
@DataJpaTest
@Import(GenreDaoJpa.class)
class GenreDaoJpaTest {

    @Autowired
    private GenreDao jpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName("получение записи по id")
    @Test
    void getById() {
        val actual = jpa.getById(3L);
        val expected = em.find(Genre.class, 3L);
        assertThat(actual).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName("создание записи")
    @Test
    void create() {
        val genre = testCreateGenre();
        val actual = jpa.create(genre);
        val expected = em.find(Genre.class, genre.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName("обновление записи")
    @Test
    void update() {
        val genre = testUpdateGenre();
        jpa.update(genre);
        val expected = jpa.getById(genre.getId());
        val actual = em.find(Genre.class, genre.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected.get());
    }

    @DisplayName("удаление записи")
    @Test
    void deleteById() {
        val genre = testDeleteByIdGenre();
        val genreId = genre.getId();
        jpa.create(genre);
        jpa.deleteById(genreId);
        em.clear();
        assertThat(em.find(Genre.class, genreId)).isNull();
    }

    private static Genre testCreateGenre() {
        return new Genre(111L, "genre_title_3");
    }

    private static Genre testUpdateGenre() {
        return new Genre(2L, "genre_title_3");
    }

    private static Genre testGetByIdGenre() {
        return new Genre(3L, "genre_title_3");
    }

    private static Genre testDeleteByIdGenre() {
        return new Genre(112L, "genre_title_3");
    }
}