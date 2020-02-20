package com.oop.java.calendar.ui;

import com.oop.java.calendar.ui.month.MonthFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Overrides MonthFrame run.
 * @author samu
 */
public class MonthRunnable implements Runnable {
    private MonthFrame frame;
    @Override
    public void run() {
        WindowAdapter window = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                exitMethod();
            }
        };
        frame = new MonthFrame(window); //To change body of generated methods, choose Tools | Templates.
    }
    private void exitMethod() {
        frame.dispose();
        System.exit(0);
    }
         
}