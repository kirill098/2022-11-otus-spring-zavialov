package ru.otus.homework.dao.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.homework.dao.BookDao;
import ru.otus.homework.model.Author;
import ru.otus.homework.model.Book;
import ru.otus.homework.model.Genre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тестирование BookDaoJdbc")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookDaoJpaTest {

    @Autowired
    private BookDao jpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName("получение записи по id")
    @Test
    void getById() {
        val actual = jpa.findById(3L);
        val expected = em.find(Book.class, 3L);
        assertThat(actual).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName("получение всех записей")
    @Test
    void getAll() {
        val actual = jpa.findAll();
        assertEquals(7, actual.size());
    }

    @DisplayName("создание записи")
    @Test
    void create() {
        val actual = jpa.save(testCreateBook());
        val expected = em.find(Book.class, actual.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName("обновление записи")
    @Test
    void update() {
        val book = testUpdateBook();
        jpa.save(book);
        val expected = jpa.findById(book.getId()).get();
        val actual = em.find(Book.class, book.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName("удаление записи")
    @Test
    void deleteById() {
        val book = testDeleteByIdBook();
        val actual = jpa.save(book);
        val bookId = actual.getId();
        jpa.deleteById(bookId);
        em.flush();
        assertThat(em.find(Book.class, bookId)).isNull();
    }

    private static Book testCreateBook() {
        return new Book("book_title_1", new Author(1L), new Genre(1L));
    }

    private static Book testUpdateBook() {
        return new Book("MyTitle", new Author(2L), new Genre(2L));
    }

    private static Book testDeleteByIdBook() {
        return new Book("book_title_3", new Author(3L), new Genre(3L));
    }

}