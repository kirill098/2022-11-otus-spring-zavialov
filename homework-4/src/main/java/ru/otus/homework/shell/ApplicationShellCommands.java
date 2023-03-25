package ru.otus.homework.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.homework.service.TestService;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationShellCommands {

    private final TestService testService;

    @ShellMethod(value = "Start testing", key = {"s", "start", "testing"})
    public void startTesting() {
        testService.begin();
    }
}
