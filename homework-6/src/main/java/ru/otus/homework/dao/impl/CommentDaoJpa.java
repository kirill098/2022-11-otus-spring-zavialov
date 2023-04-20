package ru.otus.homework.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.homework.dao.CommentDao;
import ru.otus.homework.model.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CommentDaoJpa implements CommentDao {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<Comment> getById(Long id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    public Comment create(Comment comment) {
        return em.merge(comment);
    }

    @Override
    public int update(Comment comment) {
        Query query = em.createQuery("update Comment c " +
                "set c.description = :description " +
                "where c.id = :id");
        query.setParameter("description", comment.getDescription());
        query.setParameter("id", comment.getId());
        return query.executeUpdate();
    }

    @Override
    public void deleteById(Long id) {
        Query query = em.createQuery("delete " +
                "from Comment c " +
                "where c.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
