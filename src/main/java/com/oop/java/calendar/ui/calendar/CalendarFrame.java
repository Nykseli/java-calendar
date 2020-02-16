/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oop.java.calendar.ui.calendar;

/**
 *
 * @author samu
 */
import java.awt.FlowLayout;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import com.oop.java.calendar.data.providers.MonthProvider;
import com.oop.java.calendar.data.providers.MonthView;
import com.oop.java.calendar.data.providers.TaskProvider;

/**
 * CalendarFrame draws the JPanels in com.oop.java.calendar.ui.calendar.
 *
 * This should be only public class in com.oop.java.calendar.ui.calendar.
 */
public class CalendarFrame extends JFrame implements MonthView {

    /**
     * JFrame UID
     */
    private static final long serialVersionUID = 1L;
    private WindowListener windowListener;

    private int year;
    private int month;

    public CalendarFrame(WindowListener windowListener) {
        this.windowListener = windowListener;
        month = MonthProvider.getInstance().getMonth();
        year = MonthProvider.getInstance().getYear();

        MonthProvider.getInstance().addView(this);

        setLayout();
    }

    public void setLayout() {
        setTitle("Calendar");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(windowListener);

        setLayout(new FlowLayout());
        // Month is zero based
        add(new MonthPanel(month, year));
        pack();
        // setBounds(100, 100, 400, 200);
        setVisible(true);
    }

    @Override
    public void onMonthChange() {
        year = MonthProvider.getInstance().getYear();
        month = MonthProvider.getInstance().getMonth();
        TaskProvider.getInstance().loadMonthData(month, year);

        getContentPane().removeAll();
        setLayout();
        repaint();
    }

}
