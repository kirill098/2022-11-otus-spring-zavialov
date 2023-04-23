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
        val actual = jpa.getById(3L);
        val expected = em.find(Author.class, 3L);
        assertThat(actual).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName("создание записи")
    @Test
    void create() {
        val actual = jpa.save(testCreateAuthor());
        val expected = em.find(Author.class, actual.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName("обновление записи")
    @Test
    void update() {
        val author = testUpdateAuthor();
        jpa.save(author);
        val expected = jpa.getById(author.getId()).get();
        val actual = em.find(Author.class, author.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName("удаление записи")
    @Test
    void deleteById() {
        val author = testDeleteByIdAuthor();
        val actual = jpa.save(author);
        val authorId = actual.getId();
        jpa.deleteById(authorId);
        em.flush();
        assertThat(em.find(Author.class, authorId)).isNull();
    }

    private static Author testCreateAuthor() {
        return new Author("author_name_1");
    }

    private static Author testUpdateAuthor() {
        return new Author("author_name_1");
    }

    private static Author testDeleteByIdAuthor() {
        return new Author("author_name_1");
    }
}