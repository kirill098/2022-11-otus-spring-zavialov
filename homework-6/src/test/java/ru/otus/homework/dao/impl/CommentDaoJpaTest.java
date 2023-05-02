package ru.otus.homework.dao.impl;

import lombok.val;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.homework.dao.BookDao;
import ru.otus.homework.dao.CommentDao;
import ru.otus.homework.model.Author;
import ru.otus.homework.model.Book;
import ru.otus.homework.model.Comment;
import ru.otus.homework.model.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тестирование CommentDaoJdbc")
@DataJpaTest
@Import(CommentDaoJpa.class)
class CommentDaoJpaTest {

    @Autowired
    private CommentDao jpa;

    @Autowired
    private TestEntityManager em;

    @DisplayName("получение записи по id")
    @Test
    void getById() {
        val commentId = jpa.save(testCreateComment()).getId();
        val expected = jpa.getById(commentId);
        val actual = em.find(Comment.class, commentId);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected.orElse(null));
    }

    @DisplayName("создание записи")
    @Test
    void create() {
        val expected = jpa.save(testCreateComment());
        val actual = em.find(Comment.class, expected.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName("обновление записи")
    @Test
    void update() {
        val expected = jpa.save(testUpdateComment());
        val actual = em.find(Comment.class, expected.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName("удаление записи")
    @Test
    void deleteById() {
        val actual = jpa.save(testDeleteByIdComment());
        val commentId = actual.getId();
        jpa.deleteById(commentId);
        em.flush();
        assertThat(em.find(Comment.class, commentId)).isNull();
    }

    private Comment testCreateComment() {

        return new Comment("comment_name_1", createBook());
    }

    private Comment testUpdateComment() {
        return new Comment("comment_name_2", createBook());
    }

    private Comment testDeleteByIdComment() {
        return new Comment("comment_name_3", createBook());
    }

    private Book createBook() {
        val genre = em.persist(new Genre("TestGenre"));
        val author = em.persist(new Author("TestAuthor"));
        return em.persist(new Book("title", author, genre));
    }
}