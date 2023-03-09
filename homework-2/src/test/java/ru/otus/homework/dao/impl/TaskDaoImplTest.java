package ru.otus.homework.dao.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import ru.otus.homework.dao.TaskDao;
import ru.otus.homework.model.Task;
import ru.otus.homework.util.Common;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.otus.homework.util.Common.getTasks;

@DisplayName("Тестирование TaskDao")
@Execution(ExecutionMode.CONCURRENT)
class TaskDaoImplTest {
    private TaskDao taskDao;

    @BeforeEach
    void setUp() {
        taskDao = new TaskDaoImpl("test-tasks.csv");
    }

    @DisplayName("должен верно читать задания из csv файла")
    @Test
    void getAll() {
        List<Task> expectedTasks = getTasks();
        List<Task> actualTasks = taskDao.getAll();
        assertThat(actualTasks).hasSize(3).isEqualTo(expectedTasks);
    }
}