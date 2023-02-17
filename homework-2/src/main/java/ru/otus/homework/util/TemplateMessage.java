package ru.otus.homework.util;

public class TemplateMessage {

    public static final String ASK_QUESTION_TEMPLATE = "Write the correct answer\n %s=";

    public static final String SUCCEED_USER_RESULT = "Dear user, %s %s!\n" +
            "You passed the test successfully!\n" +
            "Your result is %s percent";

    public static final String FAILED_USER_RESULT = "Dear user, %s %s!\n" +
            "Unfortunately, you failed the test.\n" +
            "Your result is %s percent";
}
