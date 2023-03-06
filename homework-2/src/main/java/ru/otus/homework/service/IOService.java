package ru.otus.homework.service;

public interface IOService {

    void printLine(String text);

    void printLine(String template, Object... args);

    String readLine();
}
