/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oop.java.calendar.ui.month;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author samu
 */
public class DayNamePanel extends JPanel {
    public static final String[] NAMES = {"S", "M", "T", "W",
        "T", "F", "S"};
    private int day;

/**
 * @param day zero based day value
 *
 */
    public DayNamePanel(int day) {
        super(true);
        this.day=day;
        createLayout();
    }
    public void createLayout() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(new JLabel(NAMES[day]));
    }
}
