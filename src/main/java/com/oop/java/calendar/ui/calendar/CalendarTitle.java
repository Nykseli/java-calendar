
package com.oop.java.calendar.ui.calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.SystemColor;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

class CalendarTitle extends JPanel {

    /**
     * JPanel seriaUID
     */
    private static final long serialVersionUID = 1L;

    private JLabel label;

    CalendarTitle(String monthName, int year) {
        super(true);
        label = new JLabel(monthName + " " + year);
        label.setForeground(SystemColor.activeCaption);
        setLayout();
    }

    private void setLayout() {
        setBorder(BorderFactory.createLineBorder(SystemColor.activeCaption));
        setLayout(new FlowLayout());
        setBackground(Color.WHITE);

        add(label, BorderLayout.CENTER);
    }

}
