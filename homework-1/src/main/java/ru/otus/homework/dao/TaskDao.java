package ru.otus.homework.dao;

import com.opencsv.exceptions.CsvException;
import ru.otus.homework.model.Task;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface TaskDao {

    List<Task> getAll() throws IOException, CsvException;
}
