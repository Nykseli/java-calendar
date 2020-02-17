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
 * SetTimeFrame draws the Timetter Window.
 */
class SetTimeFrame extends AbstractFrame {

    /**
     * JFrame UID
     */
    private static final long serialVersionUID = 1L;

    private JTextField hourField;
    private JTextField minuteField;

    private boolean hourError;
    private boolean minuteError;

    SetTimeFrame(int day) {
        super("Set time", day);
    }

    SetTimeFrame(int rowIndex, Task task) {
        super("Set time", rowIndex, task);
    }

    private void addHourField(GridBagConstraints constraint) {
        Integer hourInt = task.getAlertHour();
        String hourString = hourInt != null ? hourInt.toString() : "";
        hourField = new JTextField(hourString, 2);

        add(hourField, constraint);
    }

    private void addMinuteField(GridBagConstraints constraint) {
        Integer minuteInt = task.getAlertMinute();
        String minuteString = minuteInt != null ? minuteInt.toString() : "";
        minuteField = new JTextField(minuteString, 2);

        add(minuteField, constraint);
    }

    private void addSaveButton(GridBagConstraints constraint) {
        JButton saveButton = new JButton("Save");
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

                if (hourError || minuteError) {
                    repaint();
                    return;
                }

                task.setAlertHour(hour);
                task.setAlertMinute(minute);
                TaskProvider.getInstance().updateTask(rowIndex, task);
                dispose();
            }
        });

        add(saveButton, constraint);
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
