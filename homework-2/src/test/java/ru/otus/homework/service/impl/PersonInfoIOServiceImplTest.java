package ru.otus.homework.service.impl;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import ru.otus.homework.model.TestResult;
import ru.otus.homework.model.UserData;
import ru.otus.homework.service.PersonInfoIOService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.otus.homework.util.TemplateMessages.FAILED_USER_RESULT;
import static ru.otus.homework.util.TemplateMessages.SUCCEED_USER_RESULT;


@Execution(ExecutionMode.CONCURRENT)
@DisplayName("Тестирование PersonInfoIOService")
class PersonInfoIOServiceImplTest {

    private ByteArrayOutputStream outputStream;
    private ByteArrayInputStream inputStream;
    private PersonInfoIOService personInfoIOService;

    @BeforeEach
    void setUp() {
        System.out.println(Thread.currentThread().getName());
        outputStream = new ByteArrayOutputStream();
        inputStream = new ByteArrayInputStream("Ivanov Ivan".getBytes(StandardCharsets.UTF_8));
        System.setIn(inputStream);
        personInfoIOService = new PersonInfoIOServiceImpl(new PrintStream(outputStream), new Scanner(inputStream));
    }

    @DisplayName("Сообщение, что тест пройден успешно")
    @SneakyThrows
    @Test
    void shouldPrintSucceedResult() {
        UserData user = new UserData("Ivanov", "Ivan");
        TestResult result = new TestResult(10L, 9L, 70.0f);
        personInfoIOService.printSucceedResult(user, result);
        Thread.sleep(1000);
        assertThat(outputStream).hasToString(String.format(SUCCEED_USER_RESULT,
                user.getSurname(), user.getName(), result.getUserScorePretty()));
    }

    @DisplayName("Сообщение, что тест не пройден")
    @SneakyThrows
    @Test
    void shouldPrintFailedResult() {
        UserData user = new UserData("Ivanov", "Ivan");
        TestResult result = new TestResult(10L, 9L, 70.0f);
        personInfoIOService.printFailedResult(user, result);
        Thread.sleep(1000);
        assertThat(outputStream).hasToString(String.format(FAILED_USER_RESULT,
                user.getSurname(), user.getName(), result.getUserScorePretty()));
    }

    @DisplayName("Успешное считывание UserData")
    @SneakyThrows
    @Test
    void shouldReadUserDataSuccessfully() {
        UserData expected = new UserData("Ivanov", "Ivan");
        UserData actual = personInfoIOService.readUserData("");
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("Ошибочное считывание UserData")
    @SneakyThrows
    @Test
    void shouldReadUserDataFailed() {
        outputStream = new ByteArrayOutputStream();
        inputStream = new ByteArrayInputStream("Ivanov Ivan Ivanovich".getBytes(StandardCharsets.UTF_8));
        System.setIn(inputStream);
        personInfoIOService = new PersonInfoIOServiceImpl(new PrintStream(outputStream), new Scanner(inputStream));
        Throwable th = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> personInfoIOService.readUserData(""));
        assertThat(th.getMessage()).isEqualTo("Input data is incorrect, data=[Ivanov, Ivan, Ivanovich]");
    }
}