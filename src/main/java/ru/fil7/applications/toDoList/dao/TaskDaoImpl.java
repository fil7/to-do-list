package ru.fil7.applications.toDoList.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.fil7.applications.toDoList.model.Task;

import java.util.List;

public class TaskDaoImpl implements TaskDao {

    private static final Logger logger = LoggerFactory.getLogger(Task.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addTask(Task task) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(task);
        logger.info("Task was added successfully. Task details: " + task);
    }

    @Override
    public void updateTask(Task task) {
        Session session = sessionFactory.getCurrentSession();
        session.update(task);
        logger.info("Task was updated successfully. Task details: " + task);
    }

    @Override
    public void removeTask(int id) {
        Session session = sessionFactory.getCurrentSession();
        Task task = (Task) session.get(Task.class, new Integer(id));
        if (task != null) {
            session.delete(task);
        }
        logger.info("Task was deletes successfully. Task details: " + task);
    }

    @Override
    public Task getTaskById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Task task = (Task) session.get(Task.class, new Integer(id));
        return task;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Task> listTasks() {
        Session session = sessionFactory.getCurrentSession();
        List<Task> taskList = session.createQuery("from Task").list();

        for (Task task : taskList) {
            logger.info("Task list: " + task);
        }
        return taskList;
    }

}
