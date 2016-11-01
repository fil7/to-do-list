package ru.fil7.applications.toDoList.service;

import org.springframework.transaction.annotation.Transactional;
import ru.fil7.applications.toDoList.dao.TaskDao;
import ru.fil7.applications.toDoList.model.Task;

import java.util.List;

public class TaskServiceImpl implements TaskService{

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
    public List<Task> listTasks() {
        return taskDao.listTasks();
    }


}
