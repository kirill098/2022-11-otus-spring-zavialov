package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.otus.homework.dao.BookDao;
import ru.otus.homework.exception.AlreadyExistObjectException;
import ru.otus.homework.exception.NotFoundObjectException;
import ru.otus.homework.model.Book;
import ru.otus.homework.service.BookService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao dao;

    @Override
    public Book getById(Long id) {
        try {
            return dao.getById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundObjectException("Book not found with id = %s", id);
        }
    }

    @Override
    public List<Book> getAll() {
        return dao.getAll();
    }

    @Override
    public void create(Book book) {
        try {
            dao.create(book);
        } catch (DuplicateKeyException e) {
            throw new AlreadyExistObjectException("Book has already existed with id = %s", book.getId());
        }
    }

    @Override
    public void update(Book book) {
        int count = dao.update(book);
        if (count < 1) {
            throw new NotFoundObjectException("Book not found with id = %s", book.getId());
        }
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }
}
