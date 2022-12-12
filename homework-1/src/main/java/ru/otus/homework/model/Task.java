package ru.otus.homework.model;

import lombok.Data;

import java.util.List;

@Data
public class Task {
    private Question<String> question;
    private Answer<Integer> rightAnswer;
    private List<Answer<Integer>> answers;
}
