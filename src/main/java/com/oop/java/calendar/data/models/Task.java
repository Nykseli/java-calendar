package com.oop.java.calendar.data.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.oop.java.calendar.data.db.DatabaseHelper;
import com.oop.java.calendar.data.db.Value;

public class Task extends Model {

    private static final String tableName = "task";
    public static final String foreignId = "taskId";

    // TODO: make Task sortable by alertHour and alertMinute

    // Database variables
    private Value<Integer> id;
    private Value<Integer> day;
    private Value<Integer> year;
    private Value<Integer> month;
    private Value<Integer> alertHour;
    private Value<Integer> alertMinute;
    private Value<String> task;
    private Value<Boolean> isDone;

    public Task() {
        super(tableName);
        id = new Value<Integer>("id");
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
        addIdField();
        addIntToSchema(month.getRowName());
        addIntToSchema(day.getRowName());
        addIntToSchema(year.getRowName());
        addIntToSchema(alertHour.getRowName());
        addIntToSchema(alertMinute.getRowName());
        addBoolToSchema(isDone.getRowName());
        addStringToSchema(task.getRowName());
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

    public Integer getId() {
        return id.getValue();
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

        try {
            ResultSet rs = DatabaseHelper.getInstance().query(query);
            while (rs.next()) {
                Task task = new Task();
                task.initFromResultSet(rs);
                tasks.add(task);
            }
        } catch (SQLException e) {
            // No result found
        }

        return tasks;
    }

    @Override
    protected Value<?>[] getDbValues() {
        return new Value<?>[] { day, year, month, alertHour, alertMinute, task, isDone };
    }

}
