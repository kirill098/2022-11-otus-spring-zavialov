package ru.otus.homework.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.homework.dao.BookDao;
import ru.otus.homework.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

//@RequiredArgsConstructor
//@Repository
//public class BookDaoJpa implements BookDao {
//
//    @PersistenceContext
//    private final EntityManager em;
//
//    @Override
//    public Optional<Book> getById(Long id) {
//        return Optional.ofNullable(em.find(Book.class, id));
//    }
//
//    @Override
//    public List<Book> getAll() {
//        return em.createQuery("select b from Book b", Book.class).getResultList();
//    }
//
//    @Override
//    public Book save(Book book) {
//        if (book.getId() == null) {
//            em.persist(book);
//            return book;
//        } else {
//            return em.merge(book);
//        }
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        Book book = em.find(Book.class, id);
//        if (book != null) {
//            em.remove(book);
//        }
//    }
//}
