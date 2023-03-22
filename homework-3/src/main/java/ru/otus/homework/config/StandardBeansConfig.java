package ru.otus.homework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.PrintStream;
import java.util.Scanner;

@Configuration
public class StandardBeansConfig {

    @Bean
    public PrintStream output() {
        return System.out;
    }

    @Bean
    public Scanner input() {
        return new Scanner(System.in);
    }

}
