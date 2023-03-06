package ru.otus.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.homework.model.Task;
import ru.otus.homework.model.TestResult;
import ru.otus.homework.model.UserData;
import ru.otus.homework.service.IOService;
import ru.otus.homework.service.TaskService;
import ru.otus.homework.service.TestService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static ru.otus.homework.model.TestResult.Status.SUCCESS;
import static ru.otus.homework.util.TemplateMessages.FAILED_USER_RESULT;
import static ru.otus.homework.util.TemplateMessages.SUCCEED_USER_RESULT;

@Service
public class TestServiceImpl implements TestService {

    public static final String ASK_NAME_SURNAME_TEMPLATE = "Write, please, your surname and name: ";

    private final IOService ioService;
    private final TaskService taskService;
    private float passingScore;

    public TestServiceImpl(IOService ioService, TaskService taskService,
                           @Value("#{T(java.lang.Float).parseFloat('${passing-score:0}')}")
                           float passingScore) {
        this.ioService = ioService;
        this.taskService = taskService;
        this.passingScore = passingScore;
    }

    @Override
    public void begin() {
        UserData userData = readUserData(ASK_NAME_SURNAME_TEMPLATE);
        TestResult result = startTesting();
        informUserResult(result, userData);
    }

    private TestResult startTesting() {
        List<Task> tasks = taskService.getAll();
        long countCorrectAnswers = tasks.stream().filter(taskService::complete).count();
        return new TestResult(Long.valueOf(tasks.size()), countCorrectAnswers, passingScore);
    }

    private void informUserResult(TestResult result, UserData user) {
        if (result.getStatus() == SUCCESS) {
            printSucceedResult(user, result);
        } else {
            printFailedResult(user, result);
        }
    }

    private UserData readUserData(String description) {
        ioService.printLine(description);
        return checkAndConvertArguments(ioService.readLine().split(" "));
    }

    private void printSucceedResult(UserData user, TestResult result) {
        ioService.printLine(SUCCEED_USER_RESULT, user.getSurname(), user.getName(), result.getUserScorePretty());
    }

    private void printFailedResult(UserData user, TestResult result) {
        ioService.printLine(FAILED_USER_RESULT, user.getSurname(), user.getName(), result.getUserScorePretty());
    }

    private UserData checkAndConvertArguments(String[] args) {
        if (args == null || args.length != 2) {
            throw new IllegalArgumentException(String.format("Input data is incorrect, data=%s",
                    Arrays.stream(args).collect(Collectors.toList())));
        }
        return new UserData(args[0], args[1]);
    }
}
