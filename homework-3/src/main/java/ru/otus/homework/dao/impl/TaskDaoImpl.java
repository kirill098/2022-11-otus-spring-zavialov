package ru.otus.homework.dao.impl;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.homework.config.TestSettingsConfig;
import ru.otus.homework.dao.TaskDao;
import ru.otus.homework.exception.CsvParseException;
import ru.otus.homework.model.Task;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TaskDaoImpl implements TaskDao {

    private final TestSettingsConfig config;

    @Override
    public List<Task> getAll() {
        return parseAllTasks();
    }

    private List<Task> parseAllTasks() {
        try (InputStream input = TaskDaoImpl.class.getClassLoader().getResourceAsStream(config.getFileName())) {
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
