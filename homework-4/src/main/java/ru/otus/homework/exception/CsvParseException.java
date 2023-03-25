package ru.otus.homework.exception;

public class CsvParseException extends RuntimeException {

    public CsvParseException(Throwable th) {
        super(th);
    }
}
