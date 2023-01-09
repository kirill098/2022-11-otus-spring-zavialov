package ru.otus.homework.model;

import lombok.Data;

@Data
public class Task {
    private Question question;
    private Answer answer;
}
