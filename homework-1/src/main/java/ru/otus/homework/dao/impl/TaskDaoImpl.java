package ru.otus.homework.dao.impl;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.SneakyThrows;
import ru.otus.homework.dao.TaskDao;
import ru.otus.homework.model.Task;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class TaskDaoImpl implements TaskDao {


    private final CsvToBean<Task> parser;

    public TaskDaoImpl(String pathToFile) {
        this.parser = setParser(pathToFile);

    }

    @SneakyThrows
    private static CsvToBean<Task> setParser(String pathToFile) {
        InputStream input = TaskDaoImpl.class.getClassLoader().getResourceAsStream(pathToFile);
        if (input == null) {
            throw new FileNotFoundException(String.format("File not found for path=%s", pathToFile));
        }
        return new CsvToBeanBuilder<Task>(new InputStreamReader(input))
                .withType(Task.class)
                .withSeparator(';')
                .build();
    }

    @Override
    public List<Task> getAll() {
        return parser.parse();
    }
}
