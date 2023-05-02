package ru.otus.homework.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.homework.model.Comment;

public interface CommentDao extends JpaRepository<Comment, Long> {
}
