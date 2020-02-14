
package com.oop.java.calendar.data.providers;

import java.util.ArrayList;

import com.oop.java.calendar.data.models.Task;

public interface TaskView {
    public void loadTasks(ArrayList<Task> tasks);
}
