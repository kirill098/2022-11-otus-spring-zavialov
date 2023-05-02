package ru.otus.homework.dao.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.homework.dao.GenreDao;
import ru.otus.homework.model.Genre;

import static org.assertj.core.api.Assertions.assertThat;

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
        val genreId = jpa.save(testCreateGenre()).getId();
        val expected = jpa.getById(genreId);
        val actual = em.find(Genre.class, genreId);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected.orElse(null));
    }

    @DisplayName("создание записи")
    @Test
    void create() {
        val expected = jpa.save(testCreateGenre());
        val actual = em.find(Genre.class, expected.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName("обновление записи")
    @Test
    void update() {
        val expected = jpa.save(testUpdateGenre());
        val actual = em.find(Genre.class, expected.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName("удаление записи")
    @Test
    void deleteById() {
        val actual = jpa.save(testDeleteByIdGenre());
        val genreId = actual.getId();
        jpa.deleteById(genreId);
        em.flush();
        assertThat(em.find(Genre.class, genreId)).isNull();
    }

    private static Genre testCreateGenre() {
        return new Genre("genre_title_1");
    }

    private static Genre testUpdateGenre() {
        return new Genre("genre_title_2");
    }

    private static Genre testDeleteByIdGenre() {
        return new Genre("genre_title_3");
    }
}