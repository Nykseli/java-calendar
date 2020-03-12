/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oop.java.calendar.ui.calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * Draw the whole month panel
 */
class MonthPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private int targetYear;
    private int targetMonth;

    private String[] monthNames = { "January", "February", "March", "April", "May", "June", "July", "August",
            "September", "October", "November", "December" };

    MonthPanel(int targetMonth, int targetYear) {
        super(true);
        this.targetYear = targetYear;
        this.targetMonth = targetMonth;

        setLayout();
    }

    private void setLayout() {
        setBorder(BorderFactory.createLineBorder(SystemColor.activeCaption));
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        add(new CalendarTitle(monthNames[targetMonth], targetYear), BorderLayout.NORTH);
        add(new CalendarDays(), BorderLayout.SOUTH);
    }

}
