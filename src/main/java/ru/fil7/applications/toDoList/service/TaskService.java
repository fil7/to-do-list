package ru.fil7.applications.toDoList.service;

import ru.fil7.applications.toDoList.controller.TaskFilter;
import ru.fil7.applications.toDoList.model.Task;

import java.util.List;

public interface TaskService {

    void addTask(Task task);

    void updateTask(Task task);

    void removeTask(int id);

    Task getTaskById(int id);

    List<Task> getAllTasks(TaskFilter filter);

    // Pagination
    List<Task> getAllTasksPaginated(int start, int size);
}
