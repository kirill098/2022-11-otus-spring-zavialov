package ru.otus.homework.dao.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.homework.dao.BookDao;
import ru.otus.homework.model.Author;
import ru.otus.homework.model.Book;
import ru.otus.homework.model.Genre;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тестирование BookDaoJdbc")
@DataJpaTest
@Import(BookDaoJpa.class)
class BookDaoJpaTest {

    @Autowired
    private BookDao jpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName("получение записи по id")
    @Test
    void getById() {
        val authorId = jpa.save(createBook()).getId();
        val expected = jpa.getById(authorId);
        val actual = em.find(Book.class, authorId);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected.orElse(null));
    }

    @DisplayName("получение всех записей")
    @Test
    void getAll() {
        for (int i : List.of(1, 2, 3)) {
            em.persist(createBook());
        }
        val actual = jpa.getAll();
        assertEquals(3, actual.size());
    }

    @DisplayName("создание записи")
    @Test
    void create() {
        val expected = jpa.save(createBook());
        val actual = em.find(Book.class, expected.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName("обновление записи")
    @Test
    void update() {
        val expected = jpa.save(createBook());
        val actual = em.find(Book.class, expected.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName("удаление записи")
    @Test
    void deleteById() {
        val actual = jpa.save(createBook());
        val bookId = actual.getId();
        jpa.deleteById(bookId);
        em.flush();
        assertThat(em.find(Book.class, bookId)).isNull();
    }

    private Book createBook() {
        val genre = em.persist(new Genre("TestGenre"));
        val author = em.persist(new Author("TestAuthor"));
        return new Book("title", author, genre);
    }

}