package ru.fil7.applications.toDoList.controller;

import ru.fil7.applications.toDoList.model.Task;

import java.util.function.Predicate;

public enum TaskFilter implements Predicate<Task> {
    ALL_TASKS(task -> true),
    UNCOMPLETED_TASKS(task -> !isCompleted(task)),
    COMPLETED_TASKS(task -> isCompleted(task));

    private final Predicate<Task> predicate;

    TaskFilter(Predicate<Task> predicate) {
        this.predicate = predicate;
    }

    static boolean isCompleted(Task task) {
        return task.getState() == 1;
    }

    public boolean test(Task s) {
        return predicate.test(s);
    }
}
