package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework.dao.AuthorDao;
import ru.otus.homework.exception.AlreadyExistObjectException;
import ru.otus.homework.exception.NotFoundObjectException;
import ru.otus.homework.model.Author;
import ru.otus.homework.service.AuthorService;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao dao;

    @Override
    public Author getById(Long id) {
        return dao.getById(id).orElseThrow(() -> new NotFoundObjectException("Author not found with id = %s", id));
    }

    @Override
    public void create(Author author) {
        try {
            dao.create(author);
        } catch (DuplicateKeyException e) {
            throw new AlreadyExistObjectException("Author has already existed with id = %s", author.getId());
        }
    }

    @Override
    public void update(Author author) {
        int count = dao.update(author);
        if (count < 1) {
            throw new NotFoundObjectException("Author not found with id = %s", author.getId());
        }
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }
}
