/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oop.java.calendar;

/**
 *
 * @author samu
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.oop.java.calendar.ui.month.TitlePanel;
import com.oop.java.calendar.ui.month.DayNamePanel;
import com.oop.java.calendar.ui.month.DayPanel;

public class MonthPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    protected int month;
    protected int year;

    protected String[] monthNames = {"January", "February",
        "March", "April", "May", "June", "July", "August", "September",
        "October", "November", "December"};

    protected String[] dayNames = {"S", "M", "T", "W",
        "T", "F", "S"};

    public MonthPanel(int month, int year) {
        this.month = month;
        this.year = year;

        this.add(createGUI());
    }

    protected JPanel createGUI() {
        JPanel monthPanel = new JPanel(true);
        monthPanel.setBorder(BorderFactory
                .createLineBorder(SystemColor.activeCaption));
        monthPanel.setLayout(new BorderLayout());
        monthPanel.setBackground(Color.BLACK);
        monthPanel.setForeground(Color.WHITE);
        monthPanel.add(new TitlePanel(1,2020), BorderLayout.NORTH);
        monthPanel.add(createDaysGUI(), BorderLayout.SOUTH);

        return monthPanel;
    }
    

    protected JPanel createDaysGUI() {
        JPanel dayPanel = new JPanel(true);
        dayPanel.setLayout(new GridLayout(0, dayNames.length));

        Calendar today = Calendar.getInstance();
        int tMonth = today.get(Calendar.MONTH);
        int tYear = today.get(Calendar.YEAR);
        int tDay = today.get(Calendar.DAY_OF_MONTH);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        Calendar iterator = (Calendar) calendar.clone();
        iterator.add(Calendar.DAY_OF_MONTH,
                -(iterator.get(Calendar.DAY_OF_WEEK) - 1));

        Calendar maximum = (Calendar) calendar.clone();
        maximum.add(Calendar.MONTH, +1);

        //Creates panel for days
        for (int i = 0; i < dayNames.length; i++) {
            dayPanel.add(new DayNamePanel(i));
        }

        int count = 0;
        int limit = dayNames.length * 6;

        while (iterator.getTimeInMillis() < maximum.getTimeInMillis()) {
            int lMonth = iterator.get(Calendar.MONTH);
            int lYear = iterator.get(Calendar.YEAR);


            if ((lMonth == month) && (lYear == year)) {
                int lDay = iterator.get(Calendar.DAY_OF_MONTH);
                String dayString = Integer.toString(lDay);
                if ((tMonth == month) && (tYear == year) && (tDay == lDay)) {
                    dayPanel.add(new DayPanel(dayString, true));
                } else {
                    dayPanel.add(new DayPanel(dayString, false));
                }
            } else {
                dayPanel.add(DayPanel.empty());
            }
            iterator.add(Calendar.DAY_OF_YEAR, +1);
            count++;
        }

        for (int i = count; i < limit; i++) {
            dayPanel.add(DayPanel.empty());
        }

        return dayPanel;
    }

}
