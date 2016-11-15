package ru.fil7.applications.toDoList.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.fil7.applications.toDoList.model.Task;
import ru.fil7.applications.toDoList.service.TaskService;

import java.util.List;

@RequestMapping("/rest")

@Controller
public class TaskControllerJSON {

    private TaskService taskService;
    private static final Logger logger = LoggerFactory.getLogger(TaskControllerJSON.class);


    @Autowired
    @Qualifier(value = "taskService")
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(value = TaskRestURIConstants.GET_FILTERED_TASKS, method = RequestMethod.GET)
    @ResponseBody
    public List<Task> getAllTasks(@PathVariable("filter") String filter) {
        return taskService.getAllTasks(TaskFilter.findByAbbr(filter));
    }

    @RequestMapping(value = TaskRestURIConstants.TASKS, method = RequestMethod.GET)
    @ResponseBody
    public Task getTask(@PathVariable("id") int id) {
        return taskService.getTaskById(id);
    }

    @RequestMapping(value = TaskRestURIConstants.ADD_TASK, method = RequestMethod.POST, headers = "Content-Type=application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTask(@RequestBody Task task) {
        if (task.getId() == 0) {
            taskService.addTask(task);
        } else {
            taskService.updateTask(task);
        }
    }

    @RequestMapping(value = TaskRestURIConstants.TASKS, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeTask(@PathVariable("id") int id) {
        taskService.removeTask(id);
    }

    @RequestMapping(value = TaskRestURIConstants.TASKS, method = RequestMethod.PUT, headers = "Content-Type=application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void editTask(@RequestBody Task task) {
        taskService.updateTask(task);
    }

    @RequestMapping(value = TaskRestURIConstants.GET_PAGE, method = RequestMethod.GET)
    public
    @ResponseBody
    List<Task> getPage(@PathVariable("filter") String filterId,
                       @PathVariable("start") int start,
                       @PathVariable("size") int size
    ) {
        return taskService.getAllTasksPaginated(TaskFilter.findByAbbr(filterId), start, size);
    }

}
