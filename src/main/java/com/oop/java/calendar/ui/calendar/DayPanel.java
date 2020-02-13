package com.oop.java.calendar.ui.calendar;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.oop.java.calendar.ui.day.DayFrame;

class DayPanel extends JSplitPane {

    /**
     * JPanel UID
     */
    private static final long serialVersionUID = 1L;

    private int day;
    private int year;
    private int month;
    private boolean currentDay;

    /**
     *
     * @param day        Showed day. Day panel is empty if set to < 0
     * @param month      Showed month
     * @param year       Year in xxxx format
     * @param currentDay day background is highlighted when set to true
     */
    DayPanel(int day, int month, int year, boolean currentDay) {
        super(JSplitPane.VERTICAL_SPLIT, true);
        this.currentDay = currentDay;
        this.day = day;
        this.year = year;
        this.month = month;
        setLayout();
    }

    private void setLayout() {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setDividerSize(0);

        setTopComponent(createTopPanel());

        // Don't add button in empty DayPanels
        if (day > 0)
            setBottomComponent(createBottomPanel());
    }

    private JPanel createTopPanel() {
        JPanel top = new JPanel();
        String dayString = day > 0 ? Integer.toString(day) : " ";
        JLabel label = new JLabel(dayString);

        if (currentDay)
            top.setBackground(Color.orange);
        else
            top.setBackground(Color.white);
        top.add(label);

        return top;
    }

    private JPanel createBottomPanel() {
        JPanel bottom = new JPanel();

        // TODO: get this from db
        JButton button = new JButton("Tasks: 5/6");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // TODO: If day is already open, don't repen, focus
                new DayFrame(day, month, year);
            }
        });

        bottom.add(button);

        return bottom;
    }

    /**
     * Create empty DayPanel
     */
    public static DayPanel empty() {
        return new DayPanel(-1, -1, -1, false);
    }
}
