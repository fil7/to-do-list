package ru.fil7.applications.toDoList.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(value = TaskRestURIConstants.GET_TASKS, method = RequestMethod.GET)
    public
    @ResponseBody
    List<Task> getAllTasks(@PathVariable("filter") String filter) {
        return taskService.getAllTasks(TaskFilter.findByAbbr(filter));
    }

    @RequestMapping(value = TaskRestURIConstants.GET_TASK, method = RequestMethod.GET)
    public
    @ResponseBody
    Task taskData(@RequestBody Task task) {
        logger.info("Start taskData.");
        if (checkTask(task)) {
            return taskService.getTaskById(task.getId());
        } else {
            return null;
        }
    }

    @RequestMapping(value = TaskRestURIConstants.ADD_TASK, method = RequestMethod.POST,
            headers = "Content-Type=application/json")
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        logger.info("Start addTask.");
        if (task != null) {
            if (task.getId() == 0) {
                taskService.addTask(task);
            } else {
                taskService.updateTask(task);
            }
            return new ResponseEntity<>(task, HttpStatus.OK);
        }
        return new ResponseEntity<>(task, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = TaskRestURIConstants.REMOVE_TASK, method = RequestMethod.POST,
            headers = "Content-Type=application/json")
    public ResponseEntity<Task> removeTask(@RequestBody Task task) {
        if (checkTask(task)) {
            taskService.removeTask(task.getId());
            return new ResponseEntity<>(task, HttpStatus.OK);
        }
        return new ResponseEntity<>(task, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = TaskRestURIConstants.EDIT_TASK, method = RequestMethod.POST,
            headers = "Content-Type=application/json")
    public ResponseEntity<Task> editTask(@RequestBody Task task) {
        if (checkTask(task)) {
            taskService.updateTask(task);
            return new ResponseEntity<>(task, HttpStatus.OK);
        }
        return new ResponseEntity<>(task, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = TaskRestURIConstants.CHANGE_TASK_STATE, method = RequestMethod.POST,
            headers = "Content-Type=application/json")
    public ResponseEntity<Task> changeTaskState(@RequestBody Task updatedTask) {
        if (checkTask(updatedTask)) {
            Task task = taskService.getTaskById(updatedTask.getId());
            task.setState(updatedTask.getState());
            taskService.updateTask(task);
            return new ResponseEntity<>(task, HttpStatus.OK);
        }
        return new ResponseEntity<>(updatedTask, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = TaskRestURIConstants.GET_PAGE, method = RequestMethod.GET)
    public
    @ResponseBody
    List<Task> getPage(@PathVariable("filter") String filterId,
                       @PathVariable("start") int start,
                       @PathVariable("size") int size
                       ) {
        logger.info("Start getAllTasks.");
        return taskService.getAllTasksPaginated(TaskFilter.findByAbbr(filterId), start, size);
    }

    private boolean checkTask(Task task) {
        return task != null && task.getId() > 0;
    }



}
