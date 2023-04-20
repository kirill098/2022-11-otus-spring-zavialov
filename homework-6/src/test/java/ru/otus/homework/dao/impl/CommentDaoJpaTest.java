package ru.otus.homework.dao.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.homework.dao.CommentDao;
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
        val actual = jpa.create(testCreateComment());
        val expected = em.find(Comment.class, actual.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName("обновление записи")
    @Test
    void update() {
        val comment = testUpdateComment();
        jpa.update(comment);
        val expected = jpa.getById(comment.getId()).get();
        val actual = em.find(Comment.class, comment.getId());
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @DisplayName("удаление записи")
    @Test
    void deleteById() {
        val comment = testDeleteByIdComment();
        val commentId = comment.getId();
        jpa.create(comment);
        jpa.deleteById(commentId);
        em.clear();
        assertThat(em.find(Comment.class, commentId)).isNull();
    }

    private static Comment testCreateComment() {
        return new Comment("comment_name_1", 1L);
    }

    private static Comment testUpdateComment() {
        return new Comment(2L, "comment_name_1", 1L);
    }

    private static Comment testGetByIdComment() {
        return new Comment(3L, "comment_name_3", 1L);
    }

    private static Comment testDeleteByIdComment() {
        return new Comment(112L, "comment_name_1", 1L);
    }
}