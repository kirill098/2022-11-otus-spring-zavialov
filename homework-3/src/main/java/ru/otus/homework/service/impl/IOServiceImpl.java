package ru.otus.homework.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.homework.service.IOService;
import ru.otus.homework.service.MessageService;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class IOServiceImpl implements IOService {

    private final MessageService messageService;
    private final PrintStream output;
    private final Scanner input;

    public IOServiceImpl(MessageService messageService,
                         @Value("${bean.output}") PrintStream output,
                         @Value("${bean.input}") InputStream input) {
        this.messageService = messageService;
        this.output = output;
        this.input = new Scanner(input);
    }

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
