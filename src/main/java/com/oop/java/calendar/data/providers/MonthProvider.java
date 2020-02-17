package com.oop.java.calendar.data.providers;

import java.util.Calendar;

/**
 * Provider container the current months tasks
 */
public class MonthProvider extends AbstractProvider<MonthView> {
    static MonthProvider instance = new MonthProvider();

    Calendar calendar;

    /**
     * Contains the calendar based on users local time
     */
    Calendar currentCalendar;

    private MonthProvider() {
        calendar = Calendar.getInstance();
        currentCalendar = (Calendar) calendar.clone();
    }

    public static MonthProvider getInstance() {
        return instance;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public int getDay() {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public int getMonth() {
        return calendar.get(Calendar.MONTH);
    }

    public int getYear() {
        return calendar.get(Calendar.YEAR);
    }

    public int getCurrentDay() {
        return currentCalendar.get(Calendar.DAY_OF_MONTH);
    }

    public int getCurrentMonth() {
        return currentCalendar.get(Calendar.MONTH);
    }

    public int getCurrentYear() {
        return currentCalendar.get(Calendar.YEAR);
    }

    public void nextMonth() {
        calendar.add(Calendar.MONTH, 1);
        notifyListeners();
    }

    public void previousMonth() {
        calendar.add(Calendar.MONTH, -1);
        notifyListeners();
    }

    @Override
    protected void notifyListeners() {
        for (MonthView monthView : views) {
            monthView.onMonthChange();
        }
    }

}
