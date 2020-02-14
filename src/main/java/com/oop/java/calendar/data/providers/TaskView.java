
package com.oop.java.calendar.data.providers;

import java.util.ArrayList;
import java.util.HashMap;

import com.oop.java.calendar.data.models.Task;

public interface TaskView {
    public void loadTasks(HashMap<Integer, ArrayList<Task>> tasks);
}
