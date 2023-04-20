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

import java.util.Collections;

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
        val actual = jpa.getById(3L);
        val expected = em.find(Book.class, 3L);
        assertThat(actual).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName("получение всех записей")
    @Test
    void getAll() {
        val actual = jpa.getAll();
        assertEquals(7, actual.size());
    }

    @DisplayName("создание записи")
    @Test
    void create() {
        val actual = jpa.create(testCreateBook());
        val expected = em.find(Book.class, actual.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName("обновление записи")
    @Test
    void update() {
        val book = testUpdateBook();
        jpa.update(book);
        val expected = jpa.getById(book.getId()).get();
        val actual = em.find(Book.class, book.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName("удаление записи")
    @Test
    void deleteById() {
        val book = testDeleteByIdBook();
        val bookId = book.getId();
        jpa.create(book);
        jpa.deleteById(bookId);
        em.clear();
        assertThat(em.find(Book.class, bookId)).isNull();
    }

    private static Author testCreateAuthor() {
        return new Author(111L, "author_name_1");
    }

    private static Book testCreateBook() {
        return new Book("book_title_1", new Author(1L, ""), new Genre(1L, ""), Collections.emptyList());
    }

    private static Book testUpdateBook() {
        return new Book(2L, "MyTitle", new Author(2L, ""), new Genre(2L, ""), Collections.emptyList());
    }

    private static Book testGetByIdBook() {
        return new Book(1L, "book_title_1", new Author(1L, ""), new Genre(1L, ""), Collections.emptyList());
    }

    private static Book testDeleteByIdBook() {
        return new Book(222L, "book_title_3", new Author(3L, ""), new Genre(3L, ""), Collections.emptyList());
    }

}