package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.dao.TaskDao;
import ru.otus.homework.model.Task;
import ru.otus.homework.service.TaskIOService;
import ru.otus.homework.service.TaskService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskIOService taskIOService;
    private final TaskDao dao;

    @Override
    public List<Task> getAll() {
        return dao.getAll();
    }

    @Override
    public boolean complete(Task task) {
        taskIOService.askQuestion(task);
        String userAnswer = taskIOService.getAnswer();
        return task.getAnswer().equals(userAnswer);
    }
}
