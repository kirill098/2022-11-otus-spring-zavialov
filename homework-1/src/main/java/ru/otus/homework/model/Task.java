package ru.otus.homework.model;

import com.opencsv.bean.CsvRecurse;
import lombok.Data;

@Data
public class Task {
    @CsvRecurse
    private Question question;
    @CsvRecurse
    private Answer answer;
}
