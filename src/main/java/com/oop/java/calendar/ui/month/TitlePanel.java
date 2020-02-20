/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.oop.java.calendar.ui.month;

/**
 *
 * @author samu
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Creates panel for calendar title
 * Creates border for the whole calendar
 */
public class TitlePanel extends JPanel {
    protected String[] monthNames = {"January", "February",
        "March", "April", "May", "June", "July", "August", "September",
        "October", "November", "December"};
    private int month;
    private int year;

    /**
     * @param month zero based month value
     * @param year defines year
     */
    public TitlePanel(int month, int year) {
        super(true);
        this.month = month;
        this.year = year;
        createLayout();
    }

    private void createLayout() {
        setBorder(BorderFactory.createLineBorder(SystemColor.activeCaption));
        setLayout(new FlowLayout());
        setBackground(Color.WHITE);
        JLabel label = new JLabel(monthNames[month] + " " + year);
        setForeground(SystemColor.activeCaption);

        add(label, BorderLayout.CENTER);
}

}
