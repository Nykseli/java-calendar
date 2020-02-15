package com.oop.java.calendar.data.providers;

import java.util.ArrayList;
import java.util.HashMap;

import com.oop.java.calendar.data.models.Task;

/**
 * Provider container the current months tasks
 */
public class TaskProvider {
    static TaskProvider instance = new TaskProvider();

    ArrayList<TaskView> views = new ArrayList<>();

    /**
     * Day, Tasks
     */
    private HashMap<Integer, ArrayList<Task>> tasks = new HashMap<>();

    private TaskProvider() {
    }

    public static TaskProvider getInstance() {
        return instance;
    }

    private void notifyListeners() {
        for (TaskView tv : views) {
            tv.loadTasks(tasks);
        }
    }

    private void addTask(Task task) {
        assert task.getDay() != null;

        ArrayList<Task> dayTasks;
        if (tasks.containsKey(task.getDay())) {
            dayTasks = tasks.get(task.getDay());
        } else {
            dayTasks = new ArrayList<Task>();
        }
        dayTasks.add(task);
        tasks.put(task.getDay(), dayTasks);
    }

    public void addView(TaskView view) {
        views.add(view);
    }

    public void deleteView(TaskView view) {
        views.remove(view);
    }

    /**
     * Add new task to provider and sync it with database.
     *
     * Calling this will notify view listeners.
     *
     * @param task needs to have day value set
     */
    public void addNewTask(Task task) {
        // Add new task to provider
        addTask(task);

        // Create database entry from the task
        task.create();

        // Notify listeners that tasks have been updated
        notifyListeners();
    }

    public ArrayList<Task> getDayTasks(Integer day) {
        return tasks.get(day);
    }

    public void loadMonthData(int month, int year) {
        tasks = new HashMap<>();

        // TODO: fetch data on sparate thread
        ArrayList<Task> all = Task.get(month, year);
        for (Task task : all) {
            addTask(task);
        }

        notifyListeners();
    }
}
