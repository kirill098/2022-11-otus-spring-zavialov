package ru.otus.homework.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class Task {
    @CsvBindByName(column = "question", required = true)
    private String question;
    @CsvBindByName(column = "answer", required = true)
    private String answer;
}
