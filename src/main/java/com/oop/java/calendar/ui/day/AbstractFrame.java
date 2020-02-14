package com.oop.java.calendar.ui.day;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

abstract class AbstractFrame extends JFrame {

    /**
     * JFrame UID
     */
    protected static final long serialVersionUID = 1L;

    protected int day;
    protected int month;
    protected int year;

    AbstractFrame(String title, int day, int month, int year) {
        super(title);
        assert day > 0;
        assert month > 0;
        assert year > 0;

        this.day = day;
        this.month = month;
        this.year = year;

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                onWindowClose();
            }
        });

        setLayout();
    }

    protected abstract void setLayout();

    protected abstract void onWindowClose();
}
