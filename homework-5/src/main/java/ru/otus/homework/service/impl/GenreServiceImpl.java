package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.dao.GenreDao;
import ru.otus.homework.exception.AlreadyExistObjectException;
import ru.otus.homework.exception.NotFoundObjectException;
import ru.otus.homework.model.Genre;
import ru.otus.homework.service.GenreService;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreDao dao;

    @Override
    public Genre getById(Long id) {
        Genre genre = dao.getById(id);
        if (genre == null) {
            throw new NotFoundObjectException("Genre not found with id = %s", genre.getId());
        }
        return genre;
    }

    @Override
    public void create(Genre genre) {
        try {
            dao.create(genre);
        } catch (Exception e) {
            // todo : перехватить исключение "Объект уже существует"
            throw new AlreadyExistObjectException("Genre has already existed with id = %s", genre.getId());
        }
    }

    @Override
    public void update(Genre genre) {
        int count = dao.update(genre);
        if (count < 1) {
            throw new NotFoundObjectException("Genre not found with id = %s", genre.getId());
        }
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }
}
