package com.oop.java.calendar.data.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.oop.java.calendar.data.db.DatabaseHelper;
import com.oop.java.calendar.data.db.Value;

/**
 * Task is used to model tasks in database
 */
public class Task extends Model implements Comparable<Task> {

    private static final String tableName = "task";

    // Database variables
    private Value<Integer> day;
    private Value<Integer> year;
    private Value<Integer> month;
    private Value<Integer> alertHour;
    private Value<Integer> alertMinute;
    private Value<String> task;
    private Value<Boolean> isDone;

    public Task() {
        super(tableName);
        day = new Value<Integer>("day");
        year = new Value<Integer>("year");
        month = new Value<Integer>("month");
        alertHour = new Value<Integer>("alertHour");
        alertMinute = new Value<Integer>("alertMinute");
        task = new Value<String>("task");
        isDone = new Value<Boolean>("isDone");
    }

    @Override
    protected void initialize() {
        addIntToSchema(month.getColumnName());
        addIntToSchema(day.getColumnName());
        addIntToSchema(year.getColumnName());
        addIntToSchema(alertHour.getColumnName());
        addIntToSchema(alertMinute.getColumnName());
        addBoolToSchema(isDone.getColumnName());
        addStringToSchema(task.getColumnName());
        createTable();
    }

    @Override
    public void initFromResultSet(ResultSet rs) {
        id.setValue(DatabaseHelper.getInteger(rs, "id"));
        day.setValue(DatabaseHelper.getInteger(rs, "day"));
        year.setValue(DatabaseHelper.getInteger(rs, "year"));
        month.setValue(DatabaseHelper.getInteger(rs, "month"));
        alertHour.setValue(DatabaseHelper.getInteger(rs, "alertHour"));
        alertMinute.setValue(DatabaseHelper.getInteger(rs, "alertMinute"));
        task.setValue(DatabaseHelper.getString(rs, "task"));
        isDone.setValue(DatabaseHelper.getBoolean(rs, "isDone"));
    }

    public Integer getDay() {
        return day.getValue();
    }

    public void setDay(Integer day) {
        this.day.setValue(day);
    }

    public Integer getYear() {
        return year.getValue();
    }

    public void setYear(Integer year) {
        this.year.setValue(year);
    }

    public Integer getMonth() {
        return month.getValue();
    }

    public void setMonth(Integer month) {
        this.month.setValue(month);
    }

    public Integer getAlertHour() {
        return alertHour.getValue();
    }

    public void setAlertHour(Integer alertHour) {
        this.alertHour.setValue(alertHour);
    }

    public Integer getAlertMinute() {
        return alertMinute.getValue();
    }

    public void setAlertMinute(Integer alertMinute) {
        this.alertMinute.setValue(alertMinute);
    }

    public String getTask() {
        return task.getValue();
    }

    public void setTask(String task) {
        this.task.setValue(task);
    }

    public Boolean getIsDone() {
        return isDone.getValue();
    }

    public void setIsDone(Boolean isDone) {
        this.isDone.setValue(isDone);
    }

    public static ArrayList<Task> get(int month, int year) {
        ArrayList<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM " + tableName + " WHERE month=" + month + " and year=" + year;
        query += " ORDER BY alertHour ASC, alertMinute ASC";

        try {
            ResultSet rs = DatabaseHelper.getInstance().query(query);
            while (rs.next()) {
                Task task = new Task();
                task.initFromResultSet(rs);
                tasks.add(task);
            }
            rs.close();
        } catch (SQLException e) {
            // No result found
        }

        return tasks;
    }

    @Override
    protected Value<?>[] getDbValues() {
        return new Value<?>[] { day, year, month, alertHour, alertMinute, task, isDone };
    }

    @Override
    public int compareTo(Task task) {
        int hourCompare = getAlertHour().compareTo(task.getAlertHour());
        if (hourCompare != 0)
            return hourCompare;

        return getAlertMinute().compareTo(task.getAlertMinute());
    }

}
