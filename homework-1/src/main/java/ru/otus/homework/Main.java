package ru.otus.homework;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.homework.model.Task;
import ru.otus.homework.service.TaskService;
import ru.otus.homework.service.WriterService;

import java.util.List;


public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        TaskService taskService = context.getBean(TaskService.class);
        WriterService writerService = context.getBean(WriterService.class);
        List<Task> tasks = taskService.readAll();
        writerService.writeAll(tasks);
    }
}