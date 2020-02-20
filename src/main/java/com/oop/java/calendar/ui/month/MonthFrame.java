/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oop.java.calendar.ui.month;


import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import javax.swing.JFrame;


/**
 * Creates frame for cuurent month
 * @author samu
 */
public class MonthFrame extends JFrame {
    MonthPanel panel = new MonthPanel();
    
    public MonthFrame(WindowAdapter window) {
        setTitle("Calendar");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(window);
        createLayout();
        
    }
    public void createLayout() {
        setLayout(new FlowLayout());
        add(panel);
        pack();
        // frame.setBoulds(100, 100, 400, 200);
        setVisible(true);
    }
    
    public void exitMethod() {
        dispose();
        System.exit(0);
    }
}
