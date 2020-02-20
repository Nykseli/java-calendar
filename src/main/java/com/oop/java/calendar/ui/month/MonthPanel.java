/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oop.java.calendar.ui.month;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * Creates month view layout.
 * @author samu
 */
public class MonthPanel extends JPanel {
    
    private static long serialVersionUID = 1L;

    public MonthPanel() {
        createLayout();
    }

    public void createLayout() {
        setBorder(BorderFactory.createLineBorder(SystemColor.activeCaption));
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        add(new TitlePanel(1,2020), BorderLayout.NORTH);
        add(new CalendarDays(), BorderLayout.SOUTH);
    }

}
