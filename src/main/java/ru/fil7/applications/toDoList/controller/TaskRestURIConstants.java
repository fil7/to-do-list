package ru.fil7.applications.toDoList.controller;


public class TaskRestURIConstants {
    public static final String ADD_TASK = "/tasks/add";
    public static final String EDIT_TASK_ID = "/edit/{id}";
    public static final String EDIT_TASK = "/edit/task";
    public static final String GET_ALL_TASKS = "/tasks";
    public static final String GET_COMPLETED_TASKS = "/tasks/completed-tasks";
    public static final String GET_TASK = "/taskdata/{id}";
    public static final String GET_UNCOMPLETED_TASKS = "/tasks/uncompleted-tasks";
    public static final String REMOVE_TASK_ID = "/remove/{id}";
    public static final String REMOVE_TASK = "/remove/task";
    public static final String TASK_IS_DONE_ID = "/complete/{id}";
    public static final String CHANGE_TASK_STATE = "/change-task-state";

}
