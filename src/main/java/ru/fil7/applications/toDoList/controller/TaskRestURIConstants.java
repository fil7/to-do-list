package ru.fil7.applications.toDoList.controller;


public class TaskRestURIConstants {

    public static final String ADD_TASK = "/tasks";
    public static final String TASKS = "/tasks/{id}";
    public static final String GET_FILTERED_TASKS = "/filter-tasks/{filter}";

    public static final String GET_PAGE = "/filter-tasks/{filter}/{start}/{size}";

}
