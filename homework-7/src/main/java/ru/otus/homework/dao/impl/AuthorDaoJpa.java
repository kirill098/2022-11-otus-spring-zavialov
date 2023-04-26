package ru.otus.homework.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.homework.dao.AuthorDao;
import ru.otus.homework.model.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

//@RequiredArgsConstructor
//@Repository
//public class AuthorDaoJpa implements CrudRepository<Author, Long> {
//
//    @PersistenceContext
//    private final EntityManager em;
//
//    @Override
//    public Optional<Author> getById(Long id) {
//        return Optional.ofNullable(em.find(Author.class, id));
//    }
//
//    @Override
//    public Author save(Author author) {
//        if (author.getId() == null) {
//            em.persist(author);
//            return author;
//        } else {
//            return em.merge(author);
//        }
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        Author author = em.find(Author.class, id);
//        if (author != null) {
//            em.remove(author);
//        }
//    }
//}
