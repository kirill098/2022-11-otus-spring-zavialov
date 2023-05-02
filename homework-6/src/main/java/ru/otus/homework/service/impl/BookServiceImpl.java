package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
        return dao.getById(id).orElseThrow(() -> new NotFoundObjectException("Book not found with id = %s", id));
    }

    @Override
    public List<Book> getAll() {
        return dao.getAll();
    }

    @Transactional
    @Override
    public Book create(Book book) {
        try {
            return dao.save(book);
        } catch (DuplicateKeyException e) {
            throw new AlreadyExistObjectException("Book has already existed with id = %s", book.getId());
        }
    }

    @Transactional
    @Override
    public Book update(Book book) {
        val result = dao.save(book);
        if (result == null) {
            throw new NotFoundObjectException("Book not found with id = %s", book.getId());
        }
        return result;
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }
}
