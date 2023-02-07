package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import ru.otus.homework.service.TaskOutputService;
import ru.otus.homework.service.TestService;

@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TaskOutputService taskOutputService;

    @Override
    public void begin() {
        taskOutputService.printAll();
    }
}
