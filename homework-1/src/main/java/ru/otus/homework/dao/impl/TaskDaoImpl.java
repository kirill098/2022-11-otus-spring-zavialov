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
    private CsvToBean<Task> parser;

    private void setParser() {
        if (parser != null) {
            return;
        }
        try (InputStream input = this.getClass().getClassLoader().getResourceAsStream(pathToFile)) {
            parser = new CsvToBeanBuilder<Task>(new InputStreamReader(input))
                    .withType(Task.class)
                    .withSeparator(';')
                    .build();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<Task> getAll() {
        setParser();
        return parser.parse();
    }
}
