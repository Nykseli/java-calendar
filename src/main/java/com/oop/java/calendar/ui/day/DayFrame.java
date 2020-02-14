package com.oop.java.calendar.ui.day;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.oop.java.calendar.data.models.Task;
import com.oop.java.calendar.data.providers.TaskProvider;
import com.oop.java.calendar.data.providers.TaskView;

/**
 * DayFrame draws the JPanels in com.oop.java.calendar.ui.day.
 *
 * This should be only public class in com.oop.java.calendar.ui.day.
 */
public class DayFrame extends AbstractFrame implements TaskView {

    /**
     * JFrame UID
     */
    private static final long serialVersionUID = 1L;

    private ArrayList<Task> tasks;
    private TaskProvider provider;

    private JButton newButton;
    private JButton saveButton;

    public DayFrame(int day, int month, int year) {
        super(day + "." + month + "." + year, day, month, year);

        provider = new TaskProvider(this);
        provider.loadDayData(day, month, year);
    }

    private void addNewButton(GridBagConstraints constraint) {
        if (newButton == null) {
            newButton = new JButton("Add New");
            newButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    // TODO: If AddNewFrame is already open, don't repen, focus
                    new AddNewFrame(day, month, year);
                }
            });
        }

        add(newButton, constraint);
    }

    private void addSaveButton(GridBagConstraints constraint) {
        if (saveButton == null) {
            saveButton = new JButton("Save");
        }

        add(saveButton, constraint);
    }

    @Override
    protected void setLayout() {
        setLayout(new GridBagLayout());

        GridBagConstraints constraint = new GridBagConstraints();
        constraint.insets = new Insets(10, 10, 10, 10);

        int gridY = 0;
        int taskSize = tasks != null ? tasks.size() : 0;
        for (; gridY < taskSize; gridY++) {
            JButton setTimeButton = new JButton("Set time");
            setTimeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    // TODO: If SetTImeFrame is already open, don't repen, focus
                    new SetTimeFrame(day, month, year);
                }
            });

            constraint.gridy = gridY;
            constraint.gridx = 0;
            add(new JCheckBox("Done"), constraint);
            constraint.gridx = 1;
            String task = tasks.get(gridY).getTask();
            add(new JTextField(task, 25), constraint);
            constraint.gridx = 2;
            String time = tasks.get(gridY).getAlertHour() + ":" + tasks.get(gridY).getAlertHour();
            add(new JLabel(time), constraint);
            constraint.gridx = 3;
            add(setTimeButton, constraint);
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
        // TODO: remove object from TaskProvider
        dispose();
    }

    @Override
    public void loadTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
        getContentPane().removeAll();
        setLayout();
    }

}
