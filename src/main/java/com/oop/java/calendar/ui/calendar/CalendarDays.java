package com.oop.java.calendar.ui.calendar;

import java.awt.GridLayout;
import java.util.Calendar;

import javax.swing.JPanel;

class CalendarDays extends JPanel {

    /**
     * Required JPanel UID
     */
    private static final long serialVersionUID = 1L;

    private int todayYear;
    private int todayMonth;
    private int todayDay;

    private int targetYear;
    private int targetMonth;

    // Variables for iterating days of the month
    private Calendar start;
    private Calendar end;

    CalendarDays(int targetMonth, int targetYear) {
        super(true);
        this.targetYear = targetYear;
        this.targetMonth = targetMonth;

        // Initialize todays values
        Calendar today = Calendar.getInstance();
        todayYear = today.get(Calendar.YEAR);
        todayMonth = today.get(Calendar.MONTH);
        todayDay = today.get(Calendar.DAY_OF_MONTH);

        // Inialize values for iterating days of the month
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, targetMonth);
        calendar.set(Calendar.YEAR, targetYear);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        start = (Calendar) calendar.clone();
        start.add(Calendar.DAY_OF_MONTH, -(start.get(Calendar.DAY_OF_WEEK) - 2));
        end = (Calendar) calendar.clone();
        end.add(Calendar.MONTH, +1);

        setLayout();
    }

    private void setLayout() {
        setLayout(new GridLayout(0, DayNames.names.length));

        // First add panels containing the day names
        for (int i = 0; i < DayNames.names.length; i++) {
            add(new DayNames(DayNames.names[i]));
        }

        // How many DayPanels has been added
        int count = 0;
        // How many DayPanels are going to be added
        int panelAmount = DayNames.names.length * 6;

        // Add DayPanels untill end of the target month is reached
        while (start.getTimeInMillis() < end.getTimeInMillis()) {
            int loopMonth = start.get(Calendar.MONTH);
            int loopYear = start.get(Calendar.YEAR);
            int loopDay = start.get(Calendar.DAY_OF_MONTH);

            // In every loop we add a panel and check the day
            // so we can increase these values immediately after getting loop values
            count++;
            start.add(Calendar.DAY_OF_YEAR, +1);

            // If were are not in target month and or year, add empty panels as padding
            if (loopMonth != targetMonth || loopYear != targetYear) {
                add(DayPanel.empty());
                continue;
            }

            // Else add DayPanel with the days number
            String dayString = Integer.toString(loopDay);
            boolean today = false;
            if (todayMonth == targetMonth && todayYear == targetYear && todayDay == loopDay) {
                today = true;
            }

            add(new DayPanel(dayString, today));
        }

        // Finally add empty panels as padding
        for (int i = count; i < panelAmount; i++) {
            add(DayPanel.empty());
        }
    }
}
