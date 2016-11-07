package ru.fil7.applications.toDoList.service;

import org.springframework.transaction.annotation.Transactional;
import ru.fil7.applications.toDoList.controller.TaskFilter;
import ru.fil7.applications.toDoList.dao.TaskDao;
import ru.fil7.applications.toDoList.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskServiceImpl implements TaskService {

    private TaskDao taskDao;

    public void setTaskDao(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    @Transactional
    public void addTask(Task task) {
        taskDao.addTask(task);
    }

    @Override
    @Transactional
    public void updateTask(Task task) {
        taskDao.updateTask(task);
    }

    @Override
    @Transactional
    public void removeTask(int id) {
        taskDao.removeTask(id);
    }

    @Override
    @Transactional
    public Task getTaskById(int id) {
        return taskDao.getTaskById(id);
    }

    @Override
    @Transactional
    public List<Task> getAllTasks(TaskFilter filter) {
        return taskDao.listTasks().stream()
                .filter(filter)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<Task> getAllTasksPaginated(int start, int size) {
        List<Task> list = taskDao.listTasks();
        if (start + size > list.size()) return new ArrayList<>();
        return list.subList(start, start + size);
    }

}
