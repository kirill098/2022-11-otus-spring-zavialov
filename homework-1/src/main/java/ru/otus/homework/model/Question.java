package ru.otus.homework.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class Question {
    @CsvBindByName(column = "question")
    private String title;
}
