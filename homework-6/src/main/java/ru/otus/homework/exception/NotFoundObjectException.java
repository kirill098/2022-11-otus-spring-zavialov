package ru.otus.homework.exception;

public class NotFoundObjectException extends IllegalStateException {

    public NotFoundObjectException(String template, Object... args) {
        super(String.format(template, args));
    }
}
