/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oop.java.calendar.ui.month;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Creates single panel for single day. 
 * @author samu
 */
public class DayPanel extends JPanel {

    private String day;
    private boolean currentDay;
    
    /**
     * 
     * @param day day of the panel.
     * @param currentDay checks if String day is current day.
     */
    public DayPanel(String day, boolean currentDay) {
        super(true);
        this.day = day;
        this.currentDay = currentDay;
        createLayout();
    }

    public void createLayout() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        if (currentDay) {
            setBackground(Color.ORANGE);
        }
        else {
            setBackground(Color.WHITE);
        }
        add(new JLabel(day));
    }
    
    /**
     * 
     * @return empty panel.
     */
    public static DayPanel empty() {
        return new DayPanel(" ", false);
    }
}
