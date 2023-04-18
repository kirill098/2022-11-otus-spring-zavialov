package ru.otus.homework.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.dao.GenreDao;
import ru.otus.homework.model.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class GenreDaoJpa implements GenreDao {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<Genre> getById(Long id) {
        return Optional.ofNullable(em.find(Genre.class, id));
    }

    @Override
    public Genre create(Genre genre) {
        if (genre.getId() <= 0) {
            em.persist(genre);
            return genre;
        } else {
            return em.merge(genre);
        }
    }

    @Override
    public int update(Genre genre) {
        Query query = em.createQuery("update Genre g " +
                "set g.title = :title " +
                "where g.id = :id");
        query.setParameter("title", genre.getTitle());
        query.setParameter("id", genre.getId());
        return query.executeUpdate();
    }

    @Override
    public void deleteById(Long id) {
        Query query = em.createQuery("delete " +
                "from Genre g " +
                "where g.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
