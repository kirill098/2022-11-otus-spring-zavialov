package ru.otus.homework.model;

import lombok.Data;

@Data
public class Question<T> {
    private T title;
}
