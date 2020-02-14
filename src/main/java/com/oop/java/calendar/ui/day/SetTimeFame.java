package com.oop.java.calendar.ui.day;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * SetTimeFrame draws the Timetter Window.
 */
class SetTimeFrame extends AbstractFrame {

    /**
     * JFrame UID
     */
    private static final long serialVersionUID = 1L;

    SetTimeFrame(int day, int month, int year) {
        super("Set time", day, month, year);
    }

    @Override
    protected void setLayout() {
        setLayout(new GridBagLayout());

        GridBagConstraints constraint = new GridBagConstraints();
        constraint.insets = new Insets(10, 10, 10, 10);

        constraint.gridy = 0;
        constraint.gridx = 0;
        add(new JLabel("Hour*: "), constraint);
        constraint.gridx = 1;
        // TODO: get textField default values from db
        add(new JTextField("", 2), constraint);

        constraint.gridy = 1;
        constraint.gridx = 0;
        add(new JLabel("Minute*: "), constraint);
        constraint.gridx = 1;
        add(new JTextField("", 2), constraint);

        constraint.gridy = 2;
        constraint.gridx = 3;
        constraint.gridwidth = 4;
        add(new JButton("Save"), constraint);

        setVisible(true);
        // Pack needs to be called after layout components are set
        pack();
    }

    @Override
    protected void onWindowClose() {
        dispose();
    }

}
