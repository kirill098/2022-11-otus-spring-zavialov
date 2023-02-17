package ru.otus.homework.service;

import ru.otus.homework.model.TestResult;
import ru.otus.homework.model.UserData;

public interface PersonInfoIOService {

    UserData readUserData(String description);

    void printSucceedResult(UserData user, TestResult result);

    void printFailedResult(UserData user, TestResult result);
}
