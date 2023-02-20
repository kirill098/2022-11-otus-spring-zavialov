package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.homework.model.Task;
import ru.otus.homework.model.TestResult;
import ru.otus.homework.model.UserData;
import ru.otus.homework.service.PersonInfoIOService;
import ru.otus.homework.service.TaskService;
import ru.otus.homework.service.TestService;

import java.util.List;

import static ru.otus.homework.model.TestResult.Status.SUCCESS;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    public static final String ASK_NAME_SURNAME_TEMPLATE = "Write, please, your surname and name: ";

    private final PersonInfoIOService personInfoIOService;
    private final TaskService taskService;
    @Value("#{T(java.lang.Float).parseFloat('${passing-score:0}')}")
    private float passingScore;

    @Override
    public void begin() {
        UserData userData = personInfoIOService.readUserData(ASK_NAME_SURNAME_TEMPLATE);
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
            personInfoIOService.printSucceedResult(user, result);
        } else {
            personInfoIOService.printFailedResult(user, result);
        }
    }
}
