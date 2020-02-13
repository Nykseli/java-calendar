package com.oop.java.calendar.ui.day;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * DayFrame draws the JPanels in com.oop.java.calendar.ui.day.
 *
 * This should be only public class in com.oop.java.calendar.ui.day.
 */
public class DayFrame extends JFrame {

    /**
     * JFrame UID
     */
    private static final long serialVersionUID = 1L;

    public DayFrame(int day, int month, int year) {
        super(day + "." + month + "." + year);
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

        // TODO: get these values from db
        int gridY = 0;
        for (; gridY < 2; gridY++) {
            constraint.gridy = gridY;
            constraint.gridx = 0;
            add(new JCheckBox("Done"), constraint);
            constraint.gridx = 1;
            add(new JTextField("Done", 25), constraint);
            constraint.gridx = 2;
            add(new JLabel("06:30"), constraint);
            constraint.gridx = 3;
            add(new JButton("Set time"), constraint);
        }

        constraint.gridy = gridY;
        constraint.gridx = 3;
        constraint.gridwidth = 4;
        add(new JButton("Save All"), constraint);

        setVisible(true);
        // Pack needs to be called after layout components are set
        pack();
    }

}
