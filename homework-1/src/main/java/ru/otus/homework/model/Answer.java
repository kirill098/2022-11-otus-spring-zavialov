package ru.otus.homework.model;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;

@Data
public class Answer {
    @CsvBindByName(column = "answer")
    private String title;
}
