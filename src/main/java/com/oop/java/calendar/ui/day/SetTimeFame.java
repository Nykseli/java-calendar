package com.oop.java.calendar.ui.day;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * SetTimeFrame draws the Timetter Window.
 */
class SetTimeFrame extends JFrame {

    /**
     * JFrame UID
     */
    private static final long serialVersionUID = 1L;

    SetTimeFrame(int day, int month, int year) {
        super("Set time");
        assert day > 0;
        assert month > 0;
        assert year > 0;

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                dispose();
            }
        });

        setLayout();
    }

    private void setLayout() {
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

}
