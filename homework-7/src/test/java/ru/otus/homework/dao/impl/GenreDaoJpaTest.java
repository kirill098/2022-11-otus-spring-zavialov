package ru.otus.homework.dao.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.homework.dao.GenreDao;
import ru.otus.homework.model.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тестирование GenreDaoJdbc")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class GenreDaoJpaTest {

    @Autowired
    private GenreDao jpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName("получение записи по id")
    @Test
    void getById() {
        val actual = jpa.findById(3L);
        val expected = em.find(Genre.class, 3L);
        assertThat(actual).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName("создание записи")
    @Test
    void create() {
        val actual = jpa.save(testCreateGenre());
        val expected = em.find(Genre.class, actual.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName("обновление записи")
    @Test
    void update() {
        val genre = testUpdateGenre();
        jpa.save(genre);
        val expected = jpa.findById(genre.getId());
        val actual = em.find(Genre.class, genre.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected.get());
    }

    @DisplayName("удаление записи")
    @Test
    void deleteById() {
        val genre = testDeleteByIdGenre();
        val actual = jpa.save(genre);
        val genreId = actual.getId();
        jpa.deleteById(genreId);
        em.flush();
        assertThat(em.find(Genre.class, genreId)).isNull();
    }

    private static Genre testCreateGenre() {
        return new Genre("genre_title_3");
    }

    private static Genre testUpdateGenre() {
        return new Genre("genre_title_3");
    }

    private static Genre testDeleteByIdGenre() {
        return new Genre("genre_title_3");
    }
}