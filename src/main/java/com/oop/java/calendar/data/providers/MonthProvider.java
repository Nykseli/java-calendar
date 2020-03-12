package com.oop.java.calendar.data.providers;

import java.util.Calendar;

/***
 * Month provider is used to save the Applications calender data.
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

    /**
     * Get day of the month that is currently viewed
     *
     * @return int
     */
    public int getDay() {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Get month of the year that is currently viewed
     *
     * @return int
     */
    public int getMonth() {
        return calendar.get(Calendar.MONTH);
    }

    /**
     * Get currently viewed month
     *
     * @return int
     */
    public int getYear() {
        return calendar.get(Calendar.YEAR);
    }

    /**
     * Get local day
     *
     * @return int
     */
    public int getCurrentDay() {
        return currentCalendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Get local month
     *
     * @return int
     */
    public int getCurrentMonth() {
        return currentCalendar.get(Calendar.MONTH);
    }

    /**
     * Get local year
     *
     * @return int
     */
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
