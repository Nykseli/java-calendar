/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oop.java.calendar;

import com.oop.java.calendar.ui.MonthRunnable;
import javax.swing.SwingUtilities;

/**
 *
 * @author samu
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new MonthRunnable());
    }
}
