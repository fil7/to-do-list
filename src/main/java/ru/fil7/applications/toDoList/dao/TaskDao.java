package ru.fil7.applications.toDoList.dao;


import ru.fil7.applications.toDoList.model.Task;

import java.util.List;

public interface TaskDao {

    void addTask(Task task);

    void updateTask(Task task);

    void removeTask(int id);

    Task getTaskById(int id);

    List<Task> listTasks();
}
