package ru.otus.homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.PrintStream;

@Configuration
public class ServiceConfig {

    @Bean
    public PrintStream printStream() {
        return System.out;
    }
}
