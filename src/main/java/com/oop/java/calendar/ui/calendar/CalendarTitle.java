
package com.oop.java.calendar.ui.calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.oop.java.calendar.data.providers.MonthProvider;

class CalendarTitle extends JPanel {

    /**
     * JPanel seriaUID
     */
    private static final long serialVersionUID = 1L;

    private JLabel label;

    private JButton next;
    private JButton previous;

    CalendarTitle(String monthName, int year) {
        super(true);
        label = new JLabel(monthName + " " + year);
        label.setForeground(SystemColor.activeCaption);

        next = new JButton(">");
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                MonthProvider.getInstance().nextMonth();
            }
        });

        previous = new JButton("<");
        previous.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                MonthProvider.getInstance().previousMonth();
            }
        });

        setLayout();
    }

    private void setLayout() {
        setBorder(BorderFactory.createLineBorder(SystemColor.activeCaption));
        setLayout(new FlowLayout());
        setBackground(Color.WHITE);

        add(previous, BorderLayout.CENTER);
        add(label, BorderLayout.CENTER);
        add(next, BorderLayout.CENTER);
    }

}
