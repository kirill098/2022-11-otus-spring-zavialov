package ru.otus.homework.dao;

import ru.otus.homework.model.Task;

import java.util.List;

public interface TaskDao {

    List<Task> getAll();
}
