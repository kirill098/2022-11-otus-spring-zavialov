package ru.otus.homework.dao.impl;

import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import ru.otus.homework.dao.TaskDao;
import ru.otus.homework.model.Task;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class TaskDaoImpl implements TaskDao {

    private final String filePath;

    @Override
    public List<Task> getAll() throws IOException, CsvException {

    }

    private static List<Task> convertToTask(String answers) {

    }
}
