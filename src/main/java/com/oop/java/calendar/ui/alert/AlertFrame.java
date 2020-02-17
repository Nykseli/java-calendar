package com.oop.java.calendar.ui.alert;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.oop.java.calendar.data.models.Task;

/**
 * AlertFrame draws the alert of the tash.
 *
 * This should be only public class in com.oop.java.calendar.ui.alert.
 */
public class AlertFrame extends JFrame {

    /**
     * JFrame UID
     */
    private static final long serialVersionUID = 1L;

    private Task task;

    public AlertFrame(Task task) {
        assert task.getDay() != null;
        assert task.getMonth() != null;
        assert task.getYear() != null;
        this.task = task;

        setLayout();
    }

    private void setLayout() {
        setTitle(createTitle());
        setLayout(new GridBagLayout());
        GridBagConstraints constraint = new GridBagConstraints();
        constraint.insets = new Insets(10, 10, 10, 10);
        constraint.gridy = 0;
        constraint.gridx = 0;
        add(new JLabel("The following task should be now completed:"), constraint);
        constraint.gridy = 1;
        add(new JLabel(task.getTask()), constraint);
        setVisible(true);
        pack();
    }

    private String createTitle() {
        StringBuilder sb = new StringBuilder();
        sb.append(task.getAlertHour());
        sb.append(':');
        sb.append(task.getAlertMinute());
        sb.append(' ');
        sb.append(task.getDay());
        sb.append('.');
        sb.append(task.getMonth());
        sb.append('.');
        sb.append(task.getYear());
        return sb.toString();
    }
}
