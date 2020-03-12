package com.oop.java.calendar.ui.day;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.oop.java.calendar.data.models.Task;
import com.oop.java.calendar.data.providers.TaskProvider;

/**
 * AddNewFrame draws the a window for adding a new task for the day
 */
class AddNewFrame extends AbstractFrame {

    /**
     * JFrame UID
     */
    private static final long serialVersionUID = 1L;

    private JButton saveButton;
    private JTextField taskField;
    private JTextField hourField;
    private JTextField minuteField;

    private boolean hourError;
    private boolean minuteError;

    /**
     *
     * @param day
     * @param month
     * @param year
     */
    AddNewFrame(int day) {
        super("Add new", day);

    }

    private void addSaveButton(GridBagConstraints constraint) {
        if (saveButton == null) {
            saveButton = new JButton("Add");
            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    int hour = formatHour(hourField.getText());
                    if (hour == -1)
                        hourError = true;
                    else
                        hourError = false;

                    int minute = formatMinute(minuteField.getText());
                    if (minute == -1)
                        minuteError = true;
                    else
                        minuteError = false;

                    if (!hourError && !minuteError) {

                    Task task = new Task();
                        task.setDay(day);
                        task.setYear(year);
                        task.setMonth(month);
                        task.setIsDone(false);
                        task.setTask(taskField.getText());
                        task.setAlertHour(hour);
                        task.setAlertMinute(minute);
                        TaskProvider.getInstance().addNewTask(task);
                    }

                    repaint();
                }
            });
        }

        add(saveButton, constraint);
    }

    private void addTaskField(GridBagConstraints constraint) {
        if (taskField == null) {
            taskField = new JTextField("", 25);
        }

        add(taskField, constraint);
    }

    private void addHourField(GridBagConstraints constraint) {
        if (hourField == null) {
            hourField = new JTextField("", 2);
        }

        add(hourField, constraint);
    }

    private void addMinuteField(GridBagConstraints constraint) {
        if (minuteField == null) {
            minuteField = new JTextField("", 2);
        }

        add(minuteField, constraint);
    }

    @Override
    protected void setLayout() {
        setLayout(new GridBagLayout());

        GridBagConstraints constraint = new GridBagConstraints();
        constraint.insets = new Insets(10, 10, 10, 10);

        int gridY = 0;
        constraint.gridy = gridY;
        constraint.gridx = 0;
        add(new JLabel("Hour*: "), constraint);
        constraint.gridx = 1;
        addHourField(constraint);
        gridY++;

        if (hourError) {
            constraint.gridy = gridY;
            constraint.gridx = 0;
            add(hourErrorLabel(), constraint);
            gridY++;
        }

        constraint.gridy = gridY;
        constraint.gridx = 0;
        add(new JLabel("Minute*: "), constraint);
        constraint.gridx = 1;
        addMinuteField(constraint);
        gridY++;

        if (minuteError) {
            constraint.gridy = gridY;
            constraint.gridx = 0;
            add(minuteErrorLabel(), constraint);
            gridY++;
        }

        constraint.gridy = gridY;
        constraint.gridx = 0;
        add(new JLabel("Task name*: "), constraint);
        constraint.gridx = 1;
        addTaskField(constraint);

        constraint.gridy = gridY;
        constraint.gridx = 3;
        constraint.gridwidth = 4;
        addSaveButton(constraint);

        setVisible(true);
        // Pack needs to be called after layout components are set
        pack();
    }

    @Override
    protected void onWindowClose() {
        dispose();
    }

}
