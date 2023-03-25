package ru.otus.homework.util;

import ru.otus.homework.model.Task;

import java.util.ArrayList;
import java.util.List;

public class Common {

    public static List<Task> getTasks() {
        List<Task> expectedTasks = new ArrayList<>();
        expectedTasks.add(new Task("4-4", "0"));
        expectedTasks.add(new Task("2-2", "0"));
        expectedTasks.add(new Task("3-3", "0"));
        return expectedTasks;
    }
}
