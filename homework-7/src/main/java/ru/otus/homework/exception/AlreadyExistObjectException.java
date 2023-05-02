package ru.otus.homework.exception;

public class AlreadyExistObjectException extends RuntimeException {

    public AlreadyExistObjectException(String message, Object... args) {
        super(String.format(message, args));
    }
}
