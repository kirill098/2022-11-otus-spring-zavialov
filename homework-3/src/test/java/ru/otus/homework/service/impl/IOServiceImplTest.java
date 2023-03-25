package ru.otus.homework.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.mockito.Mockito;
import ru.otus.homework.service.IOService;
import ru.otus.homework.service.MessageService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

@Execution(ExecutionMode.CONCURRENT)
@DisplayName("Тестирование IOService")
class IOServiceImplTest {

    private ByteArrayOutputStream outputStream;
    private ByteArrayInputStream inputStream;
    private IOService ioService;

    private MessageService messageService;

    @BeforeEach
    void setUp() {
        System.out.println(Thread.currentThread().getName());
        outputStream = new ByteArrayOutputStream();
        inputStream = new ByteArrayInputStream("Ivanov Ivan".getBytes(StandardCharsets.UTF_8));
        messageService = Mockito.mock(MessageService.class);
        Mockito.when(messageService.createMessage(Mockito.any(), Mockito.any())).thenReturn("EXAMPLE");
        System.setIn(inputStream);
        ioService = new IOServiceImpl(messageService, new PrintStream(outputStream), inputStream);
    }

    @DisplayName("должен читать корректно")
    @Test
    void shouldWriteCorrectly() {
        ioService.printLine("EXAMPLE");
        assertThat(outputStream).hasToString("EXAMPLE");
    }

    @DisplayName("должен писать корректно")
    @Test
    void shouldReadCorrectly() {
        String actual = ioService.readLine();
        assertThat(actual).isEqualTo("Ivanov Ivan");
    }
}