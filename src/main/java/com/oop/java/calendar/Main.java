/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oop.java.calendar;

import javax.swing.SwingUtilities;

import com.oop.java.calendar.data.models.Models;
import com.oop.java.calendar.data.providers.TaskProvider;
import com.oop.java.calendar.ui.AlertRunnable;
import com.oop.java.calendar.ui.GuiRunnable;;

/**
 *
 * @author samu
 */
public class Main {
    public static void main(String[] args) {
        // Initilize databases before starting the Gui
        Models.initializeModels();
        // Start AlertRunnable before initializing Task provider.
        // AlertRunnable implements TaskView, so the provider gives tasks
        // to AlertRunnable after provider is initialized
        new Thread(new AlertRunnable()).start();
        // Load initial provider data before starting Gui
        TaskProvider provider = TaskProvider.getInstance();
        provider.loadMonthData(1, 2020);

        SwingUtilities.invokeLater(new GuiRunnable());
    }
}
