package ru.otus.homework.service;

import ru.otus.homework.model.Task;

import java.util.List;

public interface TaskService {

    List<Task> getAll();

    boolean complete(Task task);

}
