package ru.otus.homework.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class ApplicationShellCommands {

    @ShellMethod(value = "Start testing", key = {"s", "start", "testing"})
    public void startTesting() {
    }
}
