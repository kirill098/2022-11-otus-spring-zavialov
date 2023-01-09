package ru.otus.homework.service;

import com.opencsv.exceptions.CsvException;
import ru.otus.homework.model.Task;

import java.io.IOException;
import java.util.List;

public interface TaskService {

    List<Task> readAll() throws IOException, CsvException;
}
