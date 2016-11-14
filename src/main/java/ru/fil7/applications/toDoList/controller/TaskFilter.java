package ru.fil7.applications.toDoList.controller;

import ru.fil7.applications.toDoList.model.Task;

import java.util.function.Predicate;

public enum TaskFilter implements Predicate<Task> {
    ALL_TASKS("all", task -> true),
    UNCOMPLETED_TASKS("active", task -> !isCompleted(task)),
    COMPLETED_TASKS("completed", task -> isCompleted(task));

    private final String abbr;
    private final Predicate<Task> predicate;


    TaskFilter(String filterId, Predicate<Task> predicate) {
        this.abbr = filterId;
        this.predicate = predicate;
    }

    static boolean isCompleted(Task task) {
        return task.getState();
    }

    public boolean test(Task s) {
        return predicate.test(s);
    }

    public String getAbbr() {
        return abbr;
    }

    public static TaskFilter findByAbbr(String abbr) {
        for (TaskFilter f : values()) {
            if (f.getAbbr().equals(abbr)){
                return f;
            }
        }
        throw new IllegalArgumentException("Failed to find TaskFilter by " + abbr);
    }
}
