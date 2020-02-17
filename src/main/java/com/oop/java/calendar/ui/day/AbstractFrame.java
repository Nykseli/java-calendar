package com.oop.java.calendar.ui.day;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.oop.java.calendar.data.models.Task;
import com.oop.java.calendar.data.providers.MonthProvider;
import java.awt.Color;
import javax.swing.JLabel;

abstract class AbstractFrame extends JFrame {

    /**
     * JFrame UID
     */
    protected static final long serialVersionUID = 1L;

    protected int day;
    protected int month;
    protected int year;

    protected Task task;
    protected int rowIndex;

    AbstractFrame(String title, int day) {
        super(title);
        assert day > 0;

        MonthProvider provider = MonthProvider.getInstance();
        this.day = day;
        this.year = provider.getYear();
        this.month = provider.getMonth();

        construct();
    }

    AbstractFrame(int day) {
        super();
        assert day > 0;

        MonthProvider provider = MonthProvider.getInstance();
        this.day = day;
        this.year = provider.getYear();
        this.month = provider.getMonth();

        construct();
    }

    AbstractFrame(String title, int rowIndex, Task task) {
        super(title);
        assert task != null;

        this.task = task;
        this.rowIndex = rowIndex;

        construct();
    }

    private void construct() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                onWindowClose();
            }
        });

        setLayout();
    }

    // TODO: move formats somewhere else
    protected int formatHour(String input) {
        try {
            int num = Integer.parseInt(input);
            if (num >= 0 && num <= 23)
                return num;
        } catch (NumberFormatException e) {
        }

        return -1;
    }

    protected int formatMinute(String input) {
        try {
            int num = Integer.parseInt(input);
            if (num >= 0 && num <= 59)
                return num;
        } catch (NumberFormatException e) {
        }

        return -1;
    }

    protected JLabel hourErrorLabel() {
        JLabel label = new JLabel("Error: Hour field needs to be value between 0 and 23!");
        label.setForeground(Color.RED);
        return label;
    }

    protected JLabel minuteErrorLabel() {
        JLabel label = new JLabel("Error: Minute field needs to be value between 0 and 59!");
        label.setForeground(Color.RED);
        return label;
    }

    @Override
    public void repaint() {
        getContentPane().removeAll();
        setLayout();
        super.repaint();
    }

    protected abstract void setLayout();

    protected abstract void onWindowClose();
}
