package com.oop.java.calendar.data.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.oop.java.calendar.data.db.DatabaseHelper;
import com.oop.java.calendar.data.db.Value;

abstract class Model {
    protected DatabaseHelper dbHelper = DatabaseHelper.getInstance();
    private String tableName;

    /**
     * List of schema values used to initialize the db schema
     */
    private ArrayList<String> dbSchemaValues = new ArrayList<String>();

    Model(String tableName) {
        this.tableName = tableName;
    }

    protected void addIdField() {
        dbSchemaValues.add("id INTEGER PRIMARY KEY AUTOINCREMENT");
    }

    protected void addIntToSchema(String rowName) {
        String cmd = rowName + " INTEGER";
        dbSchemaValues.add(cmd);
    }

    protected void addBoolToSchema(String rowName) {
        String cmd = rowName + " TINYINT";
        dbSchemaValues.add(cmd);
    }

    protected void addStringToSchema(String rowName) {
        String cmd = rowName + " TEXT";
        dbSchemaValues.add(cmd);
    }

    protected void createTable() {
        StringBuilder sb = new StringBuilder("CREATE TABLE ");
        sb.append(tableName);
        sb.append(" (");
        for (int i = 0; i < dbSchemaValues.size(); i++) {
            sb.append(dbSchemaValues.get(i));
            if (i < dbSchemaValues.size() - 1)
                sb.append(", ");
        }
        sb.append(" )");

        try {
            dbHelper.execute(sb.toString());
        } catch (SQLException e) {
            System.err.println("Database table '" + tableName + "' already exists.");
        }

        // dbShemaValues are not used after this so it can be set to null and garbage
        // collected
        dbSchemaValues = null;
    }

    public void create() {
        Value<?>[] dbValues = getDbValues();

        // Build INTO names VALUES values
        StringBuilder into = new StringBuilder(" ( ");
        StringBuilder values = new StringBuilder(" ( ");
        for (int i = 0; i < dbValues.length; i++) {
            into.append(dbValues[i].getRowName());
            values.append(dbValues[i].getValue());
            if (i < dbValues.length - 1) {
                into.append(", ");
                values.append(", ");
            }
        }
        into.append(" ) ");
        values.append(" ) ");

        // Build the rest of the command
        StringBuilder sb = new StringBuilder("INSERT INTO ");
        sb.append(tableName);
        sb.append(into);
        sb.append("VALUES");
        sb.append(values);

        // Execute the command
        try {
            dbHelper.execute(sb.toString());
        } catch (SQLException e) {
            System.err.println(e.toString());
        }
    }

    protected abstract void initialize();

    protected abstract Value<?>[] getDbValues();

    public abstract void initFromResultSet(ResultSet rs);
}
