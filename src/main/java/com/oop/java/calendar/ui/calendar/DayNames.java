package com.oop.java.calendar.ui.calendar;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

class DayNames extends JPanel {

    /**
     * JPanel UID
     */
    private static final long serialVersionUID = 1L;

    public static String[] names = { "M", "T", "W", "T", "F", "S", "S" };

    private JLabel label;

    DayNames(String name) {
        super(true);
        label = new JLabel(name);
        setLayout();
    }

    private void setLayout() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(label);
    }

}
