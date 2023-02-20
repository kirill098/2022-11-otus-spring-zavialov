package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.model.Task;
import ru.otus.homework.service.TaskIOService;

import java.io.PrintStream;
import java.util.Scanner;

import static ru.otus.homework.util.TemplateMessages.ASK_QUESTION;

@Service
@RequiredArgsConstructor
public class TaskIOServiceImpl implements TaskIOService {

    private final PrintStream printStream;
    private final Scanner scanner;

    @Override
    public void askQuestion(Task task) {
        printStream.printf(ASK_QUESTION, task.getQuestion());
    }

    @Override
    public String getAnswer() {
        return scanner.nextLine();
    }
}
