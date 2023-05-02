package ru.otus.homework.dao.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.homework.dao.AuthorDao;
import ru.otus.homework.model.Author;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тестирование AuthorDaoJdbc")
@DataJpaTest
@Import(AuthorDaoJpa.class)
class AuthorDaoJpaTest {

    @Autowired
    private AuthorDao jpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName("получение записи по id")
    @Test
    void getById() {
        val authorId = jpa.save(testCreateAuthor()).getId();
        val expected = jpa.getById(authorId);
        val actual = em.find(Author.class, authorId);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected.orElse(null));
    }

    @DisplayName("создание записи")
    @Test
    void create() {
        val expected = jpa.save(testCreateAuthor());
        val actual = em.find(Author.class, expected.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
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
        val actual = jpa.save(testDeleteByIdAuthor());
        val authorId = actual.getId();
        jpa.deleteById(authorId);
        em.flush();
        assertThat(em.find(Author.class, authorId)).isNull();
    }

    private static Author testCreateAuthor() {
        return new Author("author_name_1");
    }

    private static Author testUpdateAuthor() {
        return new Author("author_name_2");
    }

    private static Author testDeleteByIdAuthor() {
        return new Author("author_name_3");
    }
}