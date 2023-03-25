package ru.otus.homework.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import ru.otus.homework.dao.TaskDao;
import ru.otus.homework.model.Task;
import ru.otus.homework.service.IOService;
import ru.otus.homework.service.TaskService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ru.otus.homework.util.Common.getTasks;

@Execution(ExecutionMode.CONCURRENT)
@DisplayName("Тестирование TaskService")
class TaskServiceImplTest {
    private TaskService taskService;
    private IOService ioService;
    private TaskDao taskDao;

    @BeforeEach
    void setUp() {
        ioService = mock(IOService.class);
        taskDao = mock(TaskDao.class);
        taskService = new TaskServiceImpl(ioService, taskDao);
    }

    @DisplayName("должен верно возвращать список заданий")
    @Test
    void getAll() {
        List<Task> expectedTasks = getTasks();
        when(taskDao.getAll()).thenReturn(expectedTasks);
        List<Task> actualTasks = taskService.getAll();
        assertThat(actualTasks).hasSize(3).isEqualTo(expectedTasks);
    }
}