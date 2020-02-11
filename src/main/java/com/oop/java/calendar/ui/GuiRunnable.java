/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oop.java.calendar.ui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import com.oop.java.calendar.ui.calendar.CalendarFrame;

public class GuiRunnable implements Runnable {

    private CalendarFrame frame;

    @Override
    public void run() {

        WindowListener listener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                exitProcedure();
            }
        };

        frame = new CalendarFrame(listener);
    }

    public void exitProcedure() {
        // TODO: make sure the state of the program is saved
        frame.dispose();
        System.exit(0);
    }

}
