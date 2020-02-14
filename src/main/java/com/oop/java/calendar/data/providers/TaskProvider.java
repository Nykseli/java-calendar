package com.oop.java.calendar.data.providers;

import com.oop.java.calendar.data.models.Task;

public class TaskProvider {

    // TODO: make this singleton and add views to list and then update them with
    // nofitylisteners
    TaskView view;

    public TaskProvider(TaskView view) {
        this.view = view;
    }

    public void loadDayData(int day, int month, int year) {
        view.loadTasks(Task.get(day, month, year));
    }
}
