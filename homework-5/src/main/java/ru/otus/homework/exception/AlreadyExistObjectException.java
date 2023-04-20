package ru.otus.homework.exception;

import javax.management.InstanceAlreadyExistsException;

public class AlreadyExistObjectException extends RuntimeException {

    public AlreadyExistObjectException(String message, Object... args) {
        super(String.format(message, args));
    }
}
