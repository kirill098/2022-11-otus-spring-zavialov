package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.model.Task;
import ru.otus.homework.service.IOService;
import ru.otus.homework.service.TaskIOService;

import static ru.otus.homework.util.TemplateMessages.ASK_QUESTION;

@Service
@RequiredArgsConstructor
public class TaskIOServiceImpl implements TaskIOService {

    private final IOService ioService;

    @Override
    public void askQuestion(Task task) {
        ioService.printLine(ASK_QUESTION, task.getQuestion());
    }

    @Override
    public String getAnswer() {
        return ioService.readLine();
    }
}
