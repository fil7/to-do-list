package ru.fil7.applications.toDoList.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.fil7.applications.toDoList.model.Task;
import ru.fil7.applications.toDoList.service.TaskService;

import java.util.List;

@RequestMapping("/rest")
@Controller
public class TaskControllerJSON {
    private static final Logger logger = LoggerFactory.getLogger(TaskControllerJSON.class);
    private TaskService taskService;

    @Autowired
    @Qualifier(value = "taskService")
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(value = TaskRestURIConstants.GET_ALL_TASKS, method = RequestMethod.GET)
    public
    @ResponseBody
    List<Task> getAllTasks() {
        logger.info("Start getAllTasks.");
        return taskService.getAllTasks(TaskFilter.ALL_TASKS);
    }

    @RequestMapping(value = TaskRestURIConstants.GET_UNCOMPLETED_TASKS, method = RequestMethod.GET)
    public
    @ResponseBody
    List<Task> getAllUncompletedTasks() {
        logger.info("Start getAllUncompletedTasks.");
        return taskService.getAllTasks(TaskFilter.UNCOMPLETED_TASKS);
    }

    @RequestMapping(value = TaskRestURIConstants.GET_COMPLETED_TASKS, method = RequestMethod.GET)
    public
    @ResponseBody
    List<Task> getAllCompletedTasks() {
        logger.info("Start getAllCompletedTasks.");
        return taskService.getAllTasks(TaskFilter.COMPLETED_TASKS);
    }

    @RequestMapping(value = TaskRestURIConstants.ADD_TASK, method = RequestMethod.POST)
    public
    @ResponseBody
    void addTask(@ModelAttribute("task") Task task) {
        logger.info("Start addTask.");
        if (task.getId() == 0) {
            taskService.addTask(task);
        } else {
            taskService.updateTask(task);
        }
    }

    @RequestMapping(value = TaskRestURIConstants.GET_TASK)
    public
    @ResponseBody
    Task taskData(@PathVariable("id") int id) {
        logger.info("Start taskData.");
        return taskService.getTaskById(id);
    }

}