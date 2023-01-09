package ru.otus.homework.service.impl;

import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import ru.otus.homework.dao.TaskDao;
import ru.otus.homework.model.Task;
import ru.otus.homework.service.TaskService;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskDao dao;

    @Override
    public List<Task> readAll() throws IOException, CsvException {
        return dao.getAll();
    }
}
