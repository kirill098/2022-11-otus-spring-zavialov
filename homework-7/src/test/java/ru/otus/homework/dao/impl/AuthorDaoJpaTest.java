package ru.otus.homework.dao.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.homework.dao.AuthorDao;
import ru.otus.homework.model.Author;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тестирование AuthorDaoJdbc")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AuthorDaoJpaTest {

    @Autowired
    private AuthorDao jpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName("получение записи по id")
    @Test
    void getById() {
        val expected = em.persist(testCreateAuthor());
        val actual = jpa.findById(expected.getId()).orElse(null);

        assertThat(actual.getName()).isEqualTo(expected.getName());
        assertThat(actual.getId()).isEqualTo(expected.getId());
        assertThat(actual.getId()).isPositive();
    }

    @DisplayName("создание записи")
    @Test
    void create() {
        val expected = jpa.save(testCreateAuthor());
        val actual = em.find(Author.class, expected.getId());

        assertThat(actual.getName()).isEqualTo(expected.getName());
        assertThat(actual.getId()).isEqualTo(expected.getId());
        assertThat(actual.getId()).isPositive();
    }

    @DisplayName("обновление записи")
    @Test
    void update() {
        val expected = jpa.save(testUpdateAuthor());
        val actual = em.find(Author.class, expected.getId());

        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName("удаление записи")
    @Test
    void deleteById() {
        val author = em.persist(testDeleteByIdAuthor());
        jpa.deleteById(author.getId());
        em.flush();
        assertThat(em.find(Author.class, author.getId())).isNull();
    }

    private static Author testCreateAuthor() {
        return new Author("author_name_1");
    }

    private static Author testUpdateAuthor() {
        return new Author(1L, "author_name_1");
    }

    private static Author testDeleteByIdAuthor() {
        return new Author("author_name_1");
    }
}