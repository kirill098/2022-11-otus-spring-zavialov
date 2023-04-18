package ru.otus.homework.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.homework.dao.AuthorDao;
import ru.otus.homework.model.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import static java.util.Collections.singletonMap;

@Repository
@RequiredArgsConstructor
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public Author getById(Long id) {
        return jdbc.queryForObject("select id, name from author where id = :id",
                singletonMap("id", id), new AuthorMapper());
    }

    @Override
    public void create(Author author) {
        Map<String, Object> params = Map.of("id", author.getId(), "name", author.getName());
        jdbc.update("insert into author (id, name) values (:id, :name)", params);
    }

    @Override
    public int update(Author author) {
        Map<String, Object> params = Map.of("id", author.getId(), "name", author.getName());
        return jdbc.update("update author set name = :name where id = :id", params);
    }

    @Override
    public int deleteById(Long id) {
        return jdbc.update("delete from author where id = :id", singletonMap("id", id));
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
