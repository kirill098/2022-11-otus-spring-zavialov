package ru.otus.homework.dao;

import ru.otus.homework.model.Comment;

import java.util.Optional;

public interface CommentDao {

    Optional<Comment> getById(Long id);

    Comment save(Comment comment);

    void deleteById(Long id);
}
