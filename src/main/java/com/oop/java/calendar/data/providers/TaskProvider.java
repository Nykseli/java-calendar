package com.oop.java.calendar.data.providers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import com.oop.java.calendar.data.models.Task;

/**
 * Provider container the current months tasks
 */
public class TaskProvider extends AbstractProvider<TaskView> {
    static TaskProvider instance = new TaskProvider();

    /**
     * Day, Tasks
     */
    private HashMap<Integer, ArrayList<Task>> tasks = new HashMap<>();

    private TaskProvider() {
    }

    public static TaskProvider getInstance() {
        return instance;
    }

    @Override
    protected void notifyListeners() {
        for (TaskView tv : views) {
            tv.loadTasks(tasks);
        }
    }

    private void addTask(Task task, boolean sort) {
        assert task.getDay() != null;

        ArrayList<Task> dayTasks;
        if (tasks.containsKey(task.getDay())) {
            dayTasks = tasks.get(task.getDay());
        } else {
            dayTasks = new ArrayList<Task>();
        }
        dayTasks.add(task);

        if (sort)
            Collections.sort(dayTasks);

        tasks.put(task.getDay(), dayTasks);
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
        addTask(task, true);

        // Create new database entry
        task.create();

        // Notify listeners that tasks have been updated
        notifyListeners();
    }

    public void removeTask(Task task) {
        assert task.getDay() != null;

        // Remove task from provider
        ArrayList<Task> list = tasks.get(task.getDay());
        list.remove(task);

        // Remove database entry
        task.delete();

        // Notify listeners that tasks have been updated
        notifyListeners();
    }

    public void updateTask(int dayIndex, Task task) {
        assert task.getDay() != null;

        // Update the task in provider
        ArrayList<Task> list = tasks.get(task.getDay());
        list.set(dayIndex, task);

        // Update database entry
        task.update();

        // Make sure tasks are sorted based on alert times
        Collections.sort(list);

        // Notify listeners that tasks have been updated
        notifyListeners();
    }

    public void updateDayTasks(Integer day, ArrayList<Task> newTasks) {
        // Update tasks to db
        // TODO: should this be a sperate thread?
        for (Task task : newTasks) {
            task.update();
        }

        // Make sure tasks are sorted based on alert times
        Collections.sort(newTasks);

        // Update the days task list
        tasks.put(day, newTasks);

        // Notify listeners that tasks have been updated
        notifyListeners();
    }

    public ArrayList<Task> getDayTasks(Integer day) {
        return tasks.get(day);
    }

    public Task getDayTask(Integer day, int dayIndex) {
        return tasks.get(day).get(dayIndex);
    }

    public void loadMonthData(int month, int year) {
        tasks = new HashMap<>();

        // TODO: fetch data on sparate thread
        ArrayList<Task> all = Task.get(month, year);
        for (Task task : all) {
            addTask(task, false);
        }

        notifyListeners();
    }
}
