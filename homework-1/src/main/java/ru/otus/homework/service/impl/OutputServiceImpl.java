package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import ru.otus.homework.model.Task;
import ru.otus.homework.service.OutputService;

import java.io.PrintStream;
import java.util.List;
import java.util.stream.IntStream;

@RequiredArgsConstructor
public class OutputServiceImpl implements OutputService {

    private final PrintStream printStream;

    @Override
    public void outputTasks(List<Task> tasks) {
        IntStream.range(0, tasks.size()).forEach(it -> {
            Task task = tasks.get(it);
            String template = "Task #%s \n" +
                    "Mathematical example: %s \n" +
                    "Right result: %s \n";
            printStream.printf(template, it + 1, task.getQuestion(), task.getAnswer());
        });
    }
}
