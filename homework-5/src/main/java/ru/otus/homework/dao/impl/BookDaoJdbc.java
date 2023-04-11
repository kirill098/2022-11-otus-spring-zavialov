package ru.otus.homework.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.homework.dao.BookDao;
import ru.otus.homework.model.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static java.util.Collections.singletonMap;

@Repository
@RequiredArgsConstructor
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations jdbc;

    @Override
    public Book getById(Long id) {
        return jdbc.queryForObject("select id, title, author_id, genre_id from book where id = :id",
                singletonMap("id", id), new BookMapper());
    }

    @Override
    public List<Book> getAll() {
        return jdbc.query("select id, title, author_id, genre_id from book", new BookMapper());
    }

    @Override
    public void create(Book book) {
        Map<String, Object> params = Map.of(
                "id", book.getId(),
                "title", book.getTitle(),
                "author_id", book.getAuthorId(),
                "genre_id", book.getGenreId()
        );
        jdbc.update("insert into book (id, title, author_id, genre_id) values (:id, :title, :author_id, :genre_id)", params);
    }

    @Override
    public void update(Book book) {
        Long bookId = book.getId();;
        if (bookId == null) {
            throw new IllegalArgumentException("id is empty");
        }
        Map<String, Object> params = Map.of(
                "id", bookId,
                "title", book.getTitle(),
                "author_id", book.getAuthorId(),
                "genre_id", book.getGenreId()
        );
        int count = jdbc.update("update book set title = :title, author_id = :author_id, genre_id = :genre_id "
                + "where id = :id", params);
        if (count == 0) {
            throw new NoSuchElementException(String.format("No book found with id=%s", bookId));
        }
    }

    @Override
    public void deleteById(Long id) {
        jdbc.update("delete from book where id = :id", singletonMap("id", id));
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long id = rs.getLong("id");
            Long author_id = rs.getLong("author_id");
            Long genre_id = rs.getLong("genre_id");
            String title = rs.getString("title");
            return new Book(id, title, author_id, genre_id);
        }
    }
}
