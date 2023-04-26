package ru.otus.homework.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.homework.dao.CommentDao;
import ru.otus.homework.model.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

//@RequiredArgsConstructor
//@Repository
//public class CommentDaoJpa implements CommentDao {
//
//    @PersistenceContext
//    private final EntityManager em;
//
//    @Override
//    public Optional<Comment> getById(Long id) {
//        return Optional.ofNullable(em.find(Comment.class, id));
//    }
//
//    @Override
//    public Comment save(Comment comment) {
//        if (comment.getId() == null) {
//            em.persist(comment);
//            return comment;
//        } else {
//            return em.merge(comment);
//        }
//    }
//
//
//    @Override
//    public void deleteById(Long id) {
//        Comment comment = em.find(Comment.class, id);
//        if (comment != null) {
//            em.remove(comment);
//        }
//        ;
//    }
//}
