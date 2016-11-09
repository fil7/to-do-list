package ru.fil7.applications.toDoList.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.fil7.applications.toDoList.model.Task;
import ru.fil7.applications.toDoList.service.TaskService;


@Controller
public class TaskController {

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    private TaskService taskService;

    @Autowired
    @Qualifier(value = "taskService")
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(value = TaskRestURIConstants.GET_ALL_TASKS, method = RequestMethod.GET)
    public String getAllTasks(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("listTasks", this.taskService.getAllTasks(TaskFilter.ALL_TASKS));

        return "tasks";
    }

    @RequestMapping(value = TaskRestURIConstants.GET_UNCOMPLETED_TASKS, method = RequestMethod.GET)
    public String getAllUncompletedTasks(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("listTasks", this.taskService.getAllTasks(TaskFilter.UNCOMPLETED_TASKS));
        return "tasks";
    }

    @RequestMapping(value = TaskRestURIConstants.GET_COMPLETED_TASKS, method = RequestMethod.GET)
    public String getAllCompletedTasks(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("listTasks", this.taskService.getAllTasks(TaskFilter.COMPLETED_TASKS));
        return "tasks";
    }

    @RequestMapping(value = TaskRestURIConstants.ADD_TASK, method = RequestMethod.POST)
    public String addTask(@ModelAttribute("task") Task task) {
        if (task.getId() == 0) {
            taskService.addTask(task);
        } else {
            taskService.updateTask(task);
        }

        return "redirect:/tasks";
    }

    @RequestMapping(TaskRestURIConstants.REMOVE_TASK_ID)
    public String removeTask(@PathVariable("id") int id) {
        taskService.removeTask(id);

        return "redirect:/tasks";
    }

    @RequestMapping(TaskRestURIConstants.EDIT_TASK_ID)
    public String editTask(@PathVariable("id") int id, Model model) {
        model.addAttribute("task", taskService.getTaskById(id));
        model.addAttribute("listTasks", this.taskService.getAllTasks(TaskFilter.ALL_TASKS));

        return "tasks";
    }

    @RequestMapping(TaskRestURIConstants.GET_TASK)
    public String taskData(@PathVariable("id") int id, Model model) {
        model.addAttribute("task", taskService.getTaskById(id));

        return "taskdata";
    }

    @RequestMapping(TaskRestURIConstants.TASK_IS_DONE)
    public String completeTask(@PathVariable("id") int id) {
        Task task = taskService.getTaskById(id);
        if (task.getState() == 0) {
            task.setState(1);
        }
        taskService.updateTask(task);

        return "redirect:/tasks";
    }


}
