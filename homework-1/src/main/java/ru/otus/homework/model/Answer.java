package ru.otus.homework.model;

import lombok.Data;

@Data
public class Answer<T> {
    private T title;
}
