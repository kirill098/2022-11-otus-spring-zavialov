package ru.otus.homework.dao.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.homework.dao.CommentDao;
import ru.otus.homework.model.Book;
import ru.otus.homework.model.Comment;

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
        val actual = jpa.getById(3L);
        val expected = em.find(Comment.class, 3L);
        assertThat(actual).isPresent().get()
                .usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName("создание записи")
    @Test
    void create() {
        val actual = jpa.save(testCreateComment());
        val expected = em.find(Comment.class, actual.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName("обновление записи")
    @Test
    void update() {
        val comment = testUpdateComment();
        jpa.save(comment);
        val expected = jpa.getById(comment.getId()).get();
        val actual = em.find(Comment.class, comment.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName("удаление записи")
    @Test
    void deleteById() {
        val comment = testDeleteByIdComment();
        val actual = jpa.save(comment);
        val commentId = actual.getId();
        jpa.deleteById(commentId);
        em.flush();
        assertThat(em.find(Comment.class, commentId)).isNull();
    }

    private static Comment testCreateComment() {
        return new Comment("comment_name_1", new Book(1L, null, null, null));
    }

    private static Comment testUpdateComment() {
        return new Comment( "comment_name_1", new Book(1L, null, null, null));
    }

    private static Comment testDeleteByIdComment() {
        return new Comment("comment_name_1", new Book(1L, null, null, null));
    }
}