package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.config.TestSettingsConfig;
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

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final IOService ioService;
    private final TaskService taskService;
    private final TestSettingsConfig config;

    @Override
    public void begin() {
        UserData userData = readUserData();
        TestResult result = startTesting();
        informUserResult(result, userData);
    }

    private TestResult startTesting() {
        List<Task> tasks = taskService.getAll();
        long countCorrectAnswers = tasks.stream().filter(taskService::complete).count();
        return new TestResult(Long.valueOf(tasks.size()), countCorrectAnswers, config.getPassingScore());
    }

    private void informUserResult(TestResult result, UserData user) {
        if (result.getStatus() == SUCCESS) {
            printSucceedResult(user, result);
        } else {
            printFailedResult(user, result);
        }
    }

    private UserData readUserData() {
        ioService.printLine("notification.surname_name");
        return checkAndConvertArguments(ioService.readLine().split(" "));
    }

    private void printSucceedResult(UserData user, TestResult result) {
        ioService.printLine("notification.succeed_user_result", user.getSurname(), user.getName(), result.getUserScorePretty());
    }

    private void printFailedResult(UserData user, TestResult result) {
        ioService.printLine("notification.failed_user_result", user.getSurname(), user.getName(), result.getUserScorePretty());
    }

    private UserData checkAndConvertArguments(String[] args) {
        if (args == null || args.length != 2) {
            throw new IllegalArgumentException(String.format("Input data is incorrect, data=%s",
                    Arrays.stream(args).collect(Collectors.toList())));
        }
        return new UserData(args[0], args[1]);
    }
}
