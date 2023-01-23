package ru.otus.homework.service.impl;

import ru.otus.homework.model.Task;
import ru.otus.homework.service.WriterService;

import java.util.List;
import java.util.stream.IntStream;

public class WriterServiceImpl implements WriterService {

    @Override
    public void writeAll(List<Task> tasks) {
        IntStream.range(0, tasks.size()).forEach(it -> {
            Task task = tasks.get(it);
            String template = "Task #%s \n" +
                    "Mathematical example: %s \n" +
                    "Right result: %s \n";
            System.out.printf(template, it + 1, task.getQuestion().getTitle(), task.getAnswer().getTitle());
        });
    }
}
