package ru.otus.homework.model;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @CsvBindByName(column = "question", required = true)
    private String question;
    @CsvBindByName(column = "answer", required = true)
    private String answer;
}
