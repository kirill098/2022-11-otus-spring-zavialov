package ru.otus.homework.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.text.DecimalFormat;

@Data
@AllArgsConstructor
public class TestResult {
    private Long countQuestions;
    private Long countCorrectAnswers;
    private Float passingScore;

    public enum Status {
        UNKNOWN, SUCCESS, FAIL
    }

    public Status getStatus() {
        if (countQuestions == null || countCorrectAnswers == null || passingScore == null) {
            return Status.UNKNOWN;
        } else if (getUserScore() >= passingScore) {
            return Status.SUCCESS;
        } else {
            return Status.FAIL;
        }
    }

    public float getUserScore() {
        return countCorrectAnswers / (float) countQuestions * 100;
    }

    public String getUserScorePretty() {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(countCorrectAnswers / (float) countQuestions * 100);
    }
}
