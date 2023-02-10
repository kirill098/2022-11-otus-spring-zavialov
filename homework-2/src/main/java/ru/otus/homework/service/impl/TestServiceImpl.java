package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.service.TaskOutputService;
import ru.otus.homework.service.TestService;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TaskOutputService taskOutputService;

    @Override
    public void begin() {
        taskOutputService.printAll();
    }
}
