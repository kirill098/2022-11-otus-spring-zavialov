package ru.otus.homework.exception;

import java.rmi.NoSuchObjectException;

public class NotFoundObjectException extends IllegalStateException {

    public NotFoundObjectException(String template, Object... args) {
        super(String.format(template, args));
    }
}
