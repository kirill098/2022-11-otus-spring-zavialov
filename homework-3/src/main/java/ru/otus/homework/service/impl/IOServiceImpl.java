package ru.otus.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homework.service.IOService;
import ru.otus.homework.service.MessageService;

import java.io.PrintStream;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class IOServiceImpl implements IOService {

    private final MessageService messageService;
    private final PrintStream output;
    private final Scanner input;

    @Override
    public void printLine(String messageCode, String... args) {
        String message = messageService.createMessage(messageCode, args);
        output.print(message);
    }

    @Override
    public String readLine() {
        return input.nextLine();
    }
}
