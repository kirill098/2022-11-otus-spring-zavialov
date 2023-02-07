package ru.otus.homework.dao.impl;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import ru.otus.homework.dao.TaskDao;
import ru.otus.homework.model.Task;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@RequiredArgsConstructor
public class TaskDaoImpl implements TaskDao {

    private final String pathToFile;

    private List<Task> parseAllTasks() {
        try (InputStream input = TaskDaoImpl.class.getClassLoader().getResourceAsStream(pathToFile)) {
            CsvToBean<Task> parser = new CsvToBeanBuilder<Task>(new InputStreamReader(input))
                    .withType(Task.class)
                    .withSeparator(';')
                    .build();
            return parser.parse();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Task> getAll() {
        return parseAllTasks();
    }
}
