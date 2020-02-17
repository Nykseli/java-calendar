package com.oop.java.calendar.ui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

import com.oop.java.calendar.data.models.Task;
import com.oop.java.calendar.data.providers.TaskProvider;
import com.oop.java.calendar.data.providers.TaskView;
import com.oop.java.calendar.ui.alert.AlertFrame;

public class AlertRunnable implements Runnable, TaskView {

    HashMap<Integer, ArrayList<Task>> tasks;
    ArrayList<Integer> alertedTasks = new ArrayList<>();

    public AlertRunnable() {
        TaskProvider.getInstance().addView(this);
    }

    @Override
    public void run() {
        while (true) {
            // Sleep for 5 seconds
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
            }

            // Cannot do anything if the tasks havent been loaded yet
            if (tasks == null)
                continue;

            LocalDateTime now = LocalDateTime.now();
            int hour = now.getHour();
            int minute = now.getMinute();

            ArrayList<Task> dailyTasks = tasks.get(now.getDayOfMonth());
            // No tasks set yet so nothing to check
            if (dailyTasks == null)
                continue;

            for (Task task : dailyTasks) {
                if (hour == task.getAlertHour() && minute == task.getAlertMinute()) {
                    // Only show the alert once per application runtime
                    if (!alertedTasks.contains(task.getId())) {
                        // TODO: play a sound?
                        alertedTasks.add(task.getId());
                        new AlertFrame(task);
                    }
                }
            }
        }
    }

    @Override
    public void loadTasks(HashMap<Integer, ArrayList<Task>> tasks) {
        this.tasks = tasks;
    }

}
