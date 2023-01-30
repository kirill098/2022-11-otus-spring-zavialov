package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import ru.otus.homework.model.Task;
import ru.otus.homework.service.OutputService;
import ru.otus.homework.service.TaskService;
import ru.otus.homework.service.TestService;

import java.util.List;

@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final OutputService outputService;
    private final TaskService taskService;

    @Override
    public void begin() {
        List<Task> tasks = taskService.readAll();
        outputService.outputTasks(tasks);
    }
}
