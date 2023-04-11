package ru.otus.homework.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.homework.dao.GenreDao;
import ru.otus.homework.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.NoSuchElementException;

import static java.util.Collections.singletonMap;

@Repository
@RequiredArgsConstructor
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public Genre getById(Long id) {
        val result = jdbc.queryForObject("select id, title from genre where id = :id",
                singletonMap("id", id), new GenreMapper());
        if (result == null) {
            throw new NoSuchElementException(String.format("No genre found with id=%s", id));
        }
        return result;
    }

    @Override
    public void create(Genre genre) {
        Map<String, Object> params = Map.of("id", genre.getId(), "title", genre.getTitle());
        jdbc.update("insert into genre (id, title) values (:id, :title)", params);
    }

    @Override
    public void update(Genre genre) {
        if (genre.getId() == null) {
            throw new IllegalArgumentException("id is empty");
        }
        Map<String, Object> params = Map.of("id", genre.getId(), "title", genre.getTitle());
        int count = jdbc.update("update genre set title = :title where id = :id", params);
        if (count == 0) {
            throw new NoSuchElementException(String.format("No genre found with id=%s", genre.getId()));
        }
    }

    @Override
    public void deleteById(Long id) {
        jdbc.update("delete from genre where id = :id", singletonMap("id", id));
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long id = rs.getLong("id");
            String title = rs.getString("title");
            return new Genre(id, title);
        }
    }
}
