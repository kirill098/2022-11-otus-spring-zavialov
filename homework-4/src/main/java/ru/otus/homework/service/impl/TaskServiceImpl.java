package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.dao.TaskDao;
import ru.otus.homework.model.Task;
import ru.otus.homework.service.IOService;
import ru.otus.homework.service.TaskService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final IOService ioService;
    private final TaskDao dao;

    @Override
    public List<Task> getAll() {
        return dao.getAll();
    }

    @Override
    public boolean complete(Task task) {
        askQuestion(task);
        String userAnswer = getAnswer();
        return task.getAnswer().equals(userAnswer);
    }

    private void askQuestion(Task task) {
        ioService.printLine("notification.answer", task.getQuestion());
    }

    private String getAnswer() {
        return ioService.readLine();
    }
}
