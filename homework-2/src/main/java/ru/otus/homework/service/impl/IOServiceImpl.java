package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.service.IOService;

import java.io.PrintStream;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class IOServiceImpl implements IOService {

    private final PrintStream printStream;
    private final Scanner scanner;

    @Override
    public void printLine(String text) {
        printStream.print(text);
    }

    @Override
    public void printLine(String template, Object... args) {
        printStream.print(String.format(template, args));
    }

    @Override
    public String readLine() {
        return scanner.nextLine();
    }
}
