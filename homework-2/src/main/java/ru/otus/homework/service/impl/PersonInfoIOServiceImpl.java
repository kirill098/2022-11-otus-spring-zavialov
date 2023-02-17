package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.model.TestResult;
import ru.otus.homework.model.UserData;
import ru.otus.homework.service.PersonInfoIOService;

import java.io.PrintStream;
import java.util.Scanner;

import static ru.otus.homework.util.TemplateMessage.FAILED_USER_RESULT;
import static ru.otus.homework.util.TemplateMessage.SUCCEED_USER_RESULT;

@Service
@RequiredArgsConstructor
public class PersonInfoIOServiceImpl implements PersonInfoIOService {

    private final PrintStream printStream;
    private final Scanner scanner;

    @Override
    public UserData readUserData(String description) {
        printStream.print(description);
        return checkAndConvertArguments(scanner.nextLine().split(" "));
    }

    @Override
    public void printSucceedResult(UserData user, TestResult result) {
        printStream.printf(SUCCEED_USER_RESULT, user.getSurname(), user.getName(), result.getUserScorePretty());
    }

    @Override
    public void printFailedResult(UserData user, TestResult result) {
        printStream.printf(FAILED_USER_RESULT, user.getSurname(), user.getName(), result.getUserScorePretty());
    }

    private UserData checkAndConvertArguments(String[] args) {
        if (args == null || args.length != 2) {
            throw new IllegalArgumentException(String.format("Input data is incorrect, data=%s", args));
        }
        return new UserData(args[0], args[1]);
    }
}
