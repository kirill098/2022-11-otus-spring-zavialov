package ru.otus.homework.service;

import ru.otus.homework.model.Task;

public interface TaskIOService {

    void askQuestion(Task task);

    String getAnswer(String description);

    String getAnswer();
}
