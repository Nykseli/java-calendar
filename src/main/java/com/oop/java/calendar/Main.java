/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oop.java.calendar;

import javax.swing.SwingUtilities;

import com.oop.java.calendar.data.models.Models;
import com.oop.java.calendar.ui.GuiRunnable;;

/**
 *
 * @author samu
 */
public class Main {
    public static void main(String[] args) {
        // Initilize databases before starting the Gui
        Models.initializeModels();

        SwingUtilities.invokeLater(new GuiRunnable());
    }
}
