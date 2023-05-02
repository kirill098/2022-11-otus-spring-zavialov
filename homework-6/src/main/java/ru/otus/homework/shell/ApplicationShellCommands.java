package ru.otus.homework.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.homework.model.Author;
import ru.otus.homework.model.Book;
import ru.otus.homework.model.Genre;
import ru.otus.homework.service.BookService;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationShellCommands {

    private final BookService bookService;

    // cb --title MyBook --authorId 1 --genreId 1
    @ShellMethod(value = "create book", key = {"cb"})
    public void createBook(@ShellOption(defaultValue = "default_book_title") String title,
                           @ShellOption(defaultValue = "1") Long authorId,
                           @ShellOption(defaultValue = "1") Long genreId) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(new Author(authorId));
        book.setGenre(new Genre(genreId));
        bookService.create(book);
    }

    @ShellMethod(value = "get all books", key = {"gab"})
    public void getAllBooks() {
        bookService.getAll().forEach(System.out::println);
    }

    // gbi --id 1
    @ShellMethod(value = "get by id", key = {"gbi"})
    public void getById(@ShellOption(defaultValue = "1") Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        Book book = bookService.getById(id);
        System.out.println(book);
    }

    // ub --id 1 --title MyBook --authorId 2 --genreId 2
    @ShellMethod(value = "update book", key = {"ub"})
    public void updateBook(@ShellOption(defaultValue = "999") Long id,
                           @ShellOption(defaultValue = "default_book_title") String title,
                           @ShellOption(defaultValue = "1") Long authorId,
                           @ShellOption(defaultValue = "1") Long genreId) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        book.setAuthor(new Author(authorId));
        book.setGenre(new Genre(genreId));
        bookService.update(book);
    }

    // dbi --id 1
    @ShellMethod(value = "delete by id", key = {"dbi"})
    public void deleteById(@ShellOption(defaultValue = "1") Long id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        bookService.deleteById(id);
    }
}
