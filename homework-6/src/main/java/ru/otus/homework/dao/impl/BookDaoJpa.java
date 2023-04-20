package ru.otus.homework.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.homework.dao.BookDao;
import ru.otus.homework.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class BookDaoJpa implements BookDao {

    @PersistenceContext
    private final EntityManager em;

    @Override
    public Optional<Book> getById(Long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public List<Book> getAll() {
        return em.createQuery("select b from Book b", Book.class).getResultList();
    }

    @Override
    public Book create(Book book) {
        return em.merge(book);
    }

    @Override
    public int update(Book book) {
        book.getComments().forEach(comment -> {
            Query queryComment = em.createQuery("update Comment c " +
                    "set c.description = :description " +
                    "where c.book_id = :book_id");
            queryComment.setParameter("description", comment.getDescription());
            queryComment.setParameter("book_id", book.getId());
            queryComment.executeUpdate();
        });

        Query queryBook = em.createQuery("update Book b " +
                "set b.title = :title, " +
                "b.author = :author, " +
                "b.genre = :genre " +
                "where b.id = :id");
        queryBook.setParameter("title", book.getTitle());
        queryBook.setParameter("author", book.getAuthor());
        queryBook.setParameter("genre", book.getGenre());
        queryBook.setParameter("id", book.getId());
        return queryBook.executeUpdate();
    }

    @Override
    public void deleteById(Long id) {
        Query query = em.createQuery("delete " +
                "from Book b " +
                "where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
