package ru.otus.homework.service;

import ru.otus.homework.model.Task;

import java.util.List;

public interface OutputService {

    void outputTasks(List<Task> tasks);
}
