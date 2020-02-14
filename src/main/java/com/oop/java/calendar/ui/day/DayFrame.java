package com.oop.java.calendar.ui.day;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * DayFrame draws the JPanels in com.oop.java.calendar.ui.day.
 *
 * This should be only public class in com.oop.java.calendar.ui.day.
 */
public class DayFrame extends AbstractFrame {

    /**
     * JFrame UID
     */
    private static final long serialVersionUID = 1L;

    private int day;
    private int month;
    private int year;

    public DayFrame(int day, int month, int year) {
        super(day + "." + month + "." + year, day, month, year);
    }

    @Override
    protected void setLayout() {
        setLayout(new GridBagLayout());

        GridBagConstraints constraint = new GridBagConstraints();
        constraint.insets = new Insets(10, 10, 10, 10);

        // TODO: get these values from db
        int gridY = 0;
        for (; gridY < 2; gridY++) {
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
            add(new JTextField("Done", 25), constraint);
            constraint.gridx = 2;
            add(new JLabel("06:30"), constraint);
            constraint.gridx = 3;
            add(setTimeButton, constraint);
        }

        constraint.gridy = gridY;
        constraint.gridwidth = 4;
        constraint.gridx = 0;
        add(new JButton("Save"), constraint);

        constraint.gridx = 3;
        JButton addNewButton = new JButton("AddNew");
        addNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // TODO: If AddNewFrame is already open, don't repen, focus
                new AddNewFrame(day, month, year);
            }
        });
        add(addNewButton, constraint);

        setVisible(true);
        // Pack needs to be called after layout components are set
        pack();
    }

    @Override
    protected void onWindowClose() {
        dispose();
    }

}
