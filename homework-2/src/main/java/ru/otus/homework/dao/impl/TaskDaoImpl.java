package ru.otus.homework.dao.impl;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.otus.homework.dao.TaskDao;
import ru.otus.homework.exception.CsvParseException;
import ru.otus.homework.model.Task;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Repository
public class TaskDaoImpl implements TaskDao {

    private String fileName;

    public TaskDaoImpl(@Value("${file.tasks.name}") String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<Task> getAll() {
        return parseAllTasks();
    }

    private List<Task> parseAllTasks() {
        try (InputStream input = TaskDaoImpl.class.getClassLoader().getResourceAsStream(fileName)) {
            CsvToBeanBuilder<Task> taskCsvToBeanBuilder = new CsvToBeanBuilder<Task>(new InputStreamReader(input));
            taskCsvToBeanBuilder.withType(Task.class);
            taskCsvToBeanBuilder.withSeparator(';');
            CsvToBean<Task> parser = taskCsvToBeanBuilder.build();
            return parser.parse();
        } catch (IOException e) {
            throw new CsvParseException(e);
        }
    }
}
