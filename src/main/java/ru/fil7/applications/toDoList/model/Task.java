package ru.fil7.applications.toDoList.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tasks")

public class Task {
    @Id
    @Column(nullable = false, name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, name = "DESCRIPTION")
    private String description;

    @Column(nullable = false, columnDefinition = "BOOLEAN", name = "STATE")
    private boolean state;

    @Column(name = "CREATED")
    private Date createdDate;

    @Column(name = "PRIORITY")
    private int priority;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", state=" + state +
                ", createdDate=" + createdDate +
                ", priority=" + priority +
                '}';
    }
}
