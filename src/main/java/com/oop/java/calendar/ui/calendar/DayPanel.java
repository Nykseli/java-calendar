package com.oop.java.calendar.ui.calendar;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

class DayPanel extends JPanel {

    /**
     * JPanel UID
     */
    private static final long serialVersionUID = 1L;

    private JLabel label;
    private boolean currentDay;

    DayPanel(String day, boolean currentDay) {
        super(true);
        this.currentDay = currentDay;
        label = new JLabel(day);
        setLayout();
    }

    private void setLayout() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        if (currentDay)
            setBackground(Color.orange);
        else
            setBackground(Color.white);

        add(label);
    }

    public static DayPanel empty() {
        return new DayPanel(" ", false);
    }
}
