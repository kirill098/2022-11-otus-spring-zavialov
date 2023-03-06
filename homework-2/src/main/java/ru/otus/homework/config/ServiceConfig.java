package ru.otus.homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.PrintStream;
import java.util.Scanner;

@Configuration
@PropertySource("classpath:application.properties")
public class ServiceConfig {

    @Bean
    public PrintStream printStream() {
        return System.out;
    }

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }
}
