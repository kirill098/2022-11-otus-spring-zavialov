package ru.otus.homework.dao.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.homework.dao.TaskDao;
import ru.otus.homework.model.Task;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.otus.homework.util.Common.getTasks;

@DisplayName("Тестирование TaskDao")
@Execution(ExecutionMode.CONCURRENT)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TaskDaoImplTest {
    @Autowired
    private TaskDao taskDao;

    @DisplayName("должен верно читать задания из csv файла")
    @Test
    void getAll() {
        List<Task> expectedTasks = getTasks();
        List<Task> actualTasks = taskDao.getAll();
        assertThat(actualTasks).hasSize(3).isEqualTo(expectedTasks);
    }
}