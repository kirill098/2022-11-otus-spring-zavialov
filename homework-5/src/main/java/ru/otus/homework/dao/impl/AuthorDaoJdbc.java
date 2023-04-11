package ru.otus.homework.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.homework.dao.AuthorDao;
import ru.otus.homework.model.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.NoSuchElementException;

import static java.util.Collections.singletonMap;

@Repository
@RequiredArgsConstructor
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public Author getById(Long id) {
        val result = jdbc.queryForObject("select id, name from author where id = :id",
                singletonMap("id", id), new AuthorMapper());
        if (result == null) {
            throw new NoSuchElementException(String.format("No author found with id=%s", id));
        }
        return result;
    }

    @Override
    public void create(Author author) {
        Map<String, Object> params = Map.of("id", author.getId(), "name", author.getName());
        jdbc.update("insert into author (id, name) values (:id, :name)", params);
    }

    @Override
    public void update(Author author) {
        if (author.getId() == null) {
            throw new IllegalArgumentException("id is empty");
        }
        Map<String, Object> params = Map.of("id", author.getId(), "name", author.getName());
        int count = jdbc.update("update author set name = :name where id = :id", params);
        if (count == 0) {
            throw new NoSuchElementException(String.format("No author found with id=%s", author.getId()));
        }
    }

    @Override
    public void deleteById(Long id) {
        jdbc.update("delete from author where id = :id", singletonMap("id", id));
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long id = rs.getLong("id");
            String name = rs.getString("name");
            return new Author(id, name);
        }
    }
}
