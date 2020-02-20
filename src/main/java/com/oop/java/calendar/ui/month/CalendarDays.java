/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oop.java.calendar.ui.month;

import java.awt.GridLayout;
import java.util.Calendar;
import javax.swing.JPanel;


/**
 * Creates panel for every day of the month, empty panel if needed.
 * @author samu
 */
class CalendarDays extends JPanel {
    private Calendar today;
    private Calendar start;
    private Calendar end;
    private int thisMonth;
    private int thisYear;
    private int thisDay;

    CalendarDays() {
        super(true);
        this.today = Calendar.getInstance();
        this.thisMonth = today.get(Calendar.MONTH);
        this.thisYear = today.get(Calendar.YEAR);
        this.thisDay = today.get(Calendar.DAY_OF_MONTH);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, thisMonth);
        calendar.set(Calendar.YEAR, thisYear);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        
        //creates starting point.
        start = (Calendar) calendar.clone();
        start.add(Calendar.DAY_OF_MONTH, - (start.get(Calendar.DAY_OF_WEEK) - 1));
        
        //creates end point.
        end = (Calendar) calendar.clone();
        end.add(Calendar.MONTH, +1);
        createLayout();
    }
    public void createLayout() {
        setLayout(new GridLayout(0, DayNamePanel.NAMES.length));
        for (int i = 0; i < DayNamePanel.NAMES.length; i++) {
            add(new DayNamePanel(i));
        }
        int count = 0;
        int limit = DayNamePanel.NAMES.length * 6;
        
        // Checks panel and either adds date or marks it empty.
        while(start.getTimeInMillis() < end.getTimeInMillis()) {
            int lMonth = start.get(Calendar.MONTH);
            int lYear = start.get(Calendar.YEAR);
            int lDay = start.get(Calendar.DAY_OF_MONTH);
            boolean isThisDay = true;
            
            start.add(Calendar.DAY_OF_YEAR, +1);
            count++;
            
            if (lMonth != thisMonth || lYear != thisYear) {
                add(DayPanel.empty());
                continue;
            }
            if (thisMonth != lMonth || thisYear != lYear || thisDay != lDay) {
                isThisDay = false;
            }
            add(new DayPanel(Integer.toString(lDay), isThisDay));                                
                
            }
        // Empty row at the end.
        for (int i = count; i<limit; i++) {
            add(DayPanel.empty());
        }
    }
}

