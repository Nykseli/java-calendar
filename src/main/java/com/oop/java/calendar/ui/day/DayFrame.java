package com.oop.java.calendar.ui.day;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.oop.java.calendar.data.models.Task;
import com.oop.java.calendar.data.providers.MonthProvider;
import com.oop.java.calendar.data.providers.TaskProvider;
import com.oop.java.calendar.data.providers.TaskView;

/**
 * DayFrame renders the day tasks view with task modification buttons.
 */
public class DayFrame extends AbstractFrame implements TaskView {

    /**
     * JFrame UID
     */
    private static final long serialVersionUID = 1L;

    private ArrayList<Task> tasks;
    private ArrayList<RowValues> rows;
    private TaskProvider provider;

    private JButton newButton;
    private JButton saveButton;

    public DayFrame(int day) {
        super(day);

        provider = TaskProvider.getInstance();
        provider.addView(this);
    }

    private void addNewButton(GridBagConstraints constraint) {
        if (newButton == null) {
            newButton = new JButton("Add New");
            newButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    // TODO: If AddNewFrame is already open, don't repen, focus
                    new AddNewFrame(day);
                }
            });
        }

        add(newButton, constraint);
    }

    private void addSaveButton(GridBagConstraints constraint) {
        if (saveButton == null) {
            saveButton = new JButton("Save");
            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    // Cannot save anything if there is no tasks
                    if (tasks == null)
                        return;

                    for (int i = 0; i < tasks.size(); i++) {
                        Task task = tasks.get(i);
                        task.setTask(rows.get(i).taskValue());
                        task.setIsDone(rows.get(i).doneValue());
                    }

                    TaskProvider.getInstance().updateDayTasks(day, tasks);
                }
            });
        }

        add(saveButton, constraint);
    }

    @Override
    protected void setLayout() {
        setTitle(day + "." + month + "." + year);
        setLayout(new GridBagLayout());
        // Reset row values
        rows = new ArrayList<>();

        GridBagConstraints constraint = new GridBagConstraints();
        constraint.insets = new Insets(10, 10, 10, 10);

        // Try to get tasks from provider if the task is not already initialized
        if (tasks == null) {
            tasks = TaskProvider.getInstance().getDayTasks(day);
        }

        int gridY = 0;
        int taskSize = tasks != null ? tasks.size() : 0;
        for (; gridY < taskSize; gridY++) {
            // Add new RowValues for each task
            RowValues values = new RowValues();

            JButton setTimeButton = new JButton("Set time");
            final Task rowTask = tasks.get(gridY);
            final int rowIndex = gridY;
            setTimeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    // TODO: If SetTImeFrame is already open, don't repen, focus
                    new SetTimeFrame(rowIndex, rowTask);
                }
            });

            constraint.gridy = gridY;
            constraint.gridx = 0;
            values.setDoneBox(tasks.get(rowIndex).getIsDone());
            add(values.getDoneBox(), constraint);

            constraint.gridx = 1;
            String task = tasks.get(rowIndex).getTask();
            values.setTaskField(task);
            add(values.getTaskField(), constraint);

            constraint.gridx = 2;
            String time = tasks.get(rowIndex).getAlertHour() + ":" + tasks.get(rowIndex).getAlertMinute();
            add(new JLabel(time), constraint);

            constraint.gridx = 3;
            add(setTimeButton, constraint);

            constraint.gridx = 4;
            values.setRemoveButton(rowTask);
            add(values.getRemoveButton(), constraint);

            // Finally add the row values to the list
            rows.add(values);
        }

        // If no tasks are added, add info label
        if (gridY == 0) {
            constraint.gridy = 0;
            add(new JLabel("No tasks available. Add a new one!"), constraint);
            gridY++;
        }

        constraint.gridy = gridY;
        constraint.gridwidth = 4;
        constraint.gridx = 0;
        addSaveButton(constraint);

        constraint.gridx = 3;
        addNewButton(constraint);

        setVisible(true);
        // Pack needs to be called after layout components are set
        pack();
    }

    @Override
    protected void onWindowClose() {
        provider.deleteView(this);
        dispose();
    }

    @Override
    public void loadTasks(HashMap<Integer, ArrayList<Task>> tasks) {
        this.tasks = tasks.get(day);

        // When tasks are reloaded, the MonthProvider year and month may have changed.
        // This makes sure that the title is painted correctly on setLayout when month
        // and/or year changes
        MonthProvider provider = MonthProvider.getInstance();
        year = provider.getYear();
        month = provider.getMonth();

        repaint();
    }

}

/**
 * Helper class for saving dayframe task rows
 */
class RowValues {
    private JCheckBox doneBox;
    private JTextField taskField;
    private JButton removeButton;

    public void setDoneBox(Boolean defaultValue) {
        boolean value = defaultValue != null ? defaultValue : false;
        doneBox = new JCheckBox("Done");
        doneBox.setSelected(value);
    }

    public JCheckBox getDoneBox() {
        return doneBox;
    }

    public boolean doneValue() {
        return doneBox.isSelected();
    }

    public void setTaskField(String defaultValue) {
        String value = defaultValue != null ? defaultValue : "";
        taskField = new JTextField(value, 25);
    }

    public JTextField getTaskField() {
        return taskField;
    }

    public String taskValue() {
        return taskField.getText();
    }

    public void setRemoveButton(final Task task) {
        removeButton = new JButton("Remove");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                TaskProvider.getInstance().removeTask(task);
            }
        });
    }

    public JButton getRemoveButton() {
        return removeButton;
    }
}
