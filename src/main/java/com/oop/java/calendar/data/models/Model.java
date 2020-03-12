package com.oop.java.calendar.data.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.oop.java.calendar.data.db.DatabaseHelper;
import com.oop.java.calendar.data.db.Value;

/**
 * Model is abstract class for database models
 */
abstract class Model {
    protected DatabaseHelper dbHelper = DatabaseHelper.getInstance();
    private String tableName;

    /**
     * Every model needs a id field
     */
    protected Value<Integer> id;

    /**
     * List of schema values used to initialize the db schema
     */
    private ArrayList<String> dbSchemaValues = new ArrayList<String>();

    Model(String tableName) {
        this.tableName = tableName;
        id = new Value<Integer>("id");
    }

    public Integer getId() {
        return id.getValue();
    }

    /**
     * Primary key id that every model should have
     */
    private void addIdField() {
        dbSchemaValues.add("id INTEGER PRIMARY KEY AUTOINCREMENT");
    }

    private Value<?>[] getModifiedDbValues() {
        Value<?>[] all = getDbValues();
        ArrayList<Value<?>> modified = new ArrayList<>();

        for (Value<?> value : all) {
            if (value.isModified())
                modified.add(value);
        }

        return modified.toArray(new Value<?>[modified.size()]);
    }

    /**
     * Assign the highest id from db to the model.
     *
     * This should be only used after model is created.
     */
    private void assignIdFromDb() throws SQLException {
        String cmd = "SELECT id FROM " + tableName + " ORDER BY id DESC LIMIT 1";

        ResultSet rs = dbHelper.query(cmd);
        id.setValue(rs.getInt("id"));
    }

    protected void addIntToSchema(String columnName) {
        String cmd = columnName + " INTEGER";
        dbSchemaValues.add(cmd);
    }

    protected void addBoolToSchema(String columnName) {
        String cmd = columnName + " BOOLEAN";
        dbSchemaValues.add(cmd);
    }

    protected void addStringToSchema(String columnName) {
        String cmd = columnName + " TEXT";
        dbSchemaValues.add(cmd);
    }

    protected void createTable() {
        // Every model needs a id field
        addIdField();
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

    /**
     * Create new entry of the model.
     *
     * Id is is automatically assigned after create.
     */
    public void create() {
        Value<?>[] dbValues = getDbValues();

        // Build INTO names VALUES values
        StringBuilder into = new StringBuilder(" ( ");
        StringBuilder values = new StringBuilder(" ( ");
        for (int i = 0; i < dbValues.length; i++) {
            into.append(dbValues[i].getColumnName());
            values.append("?");
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
            dbHelper.safeExecute(sb.toString(), dbValues);
            // TODO: is there a more efficent way to assing the Id
            assignIdFromDb();
        } catch (SQLException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Update modified database values
     */
    public void update() {
        assert id != null;
        assert id.getValue() != null;

        Value<?>[] dbValues = getModifiedDbValues();
        // If none of the values are modified, there is nothing to update
        if (dbValues.length == 0)
            return;

        // Build SET values
        StringBuilder set = new StringBuilder();
        for (int i = 0; i < dbValues.length; i++) {
            set.append(dbValues[i].getColumnName());
            set.append(" = ");
            set.append("?");
            if (i < dbValues.length - 1) {
                set.append(", ");
            }
        }

        // Build the rest of the command
        StringBuilder sb = new StringBuilder("UPDATE ");
        sb.append(tableName);
        sb.append(" SET ");
        sb.append(set);
        sb.append(" WHERE id=");
        sb.append(id.getValue());

        try {
            dbHelper.safeExecute(sb.toString(), dbValues);
        } catch (SQLException e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Delete database entry
     */
    public void delete() {
        assert id != null;
        assert id.getValue() != null;

        StringBuilder sb = new StringBuilder("DELETE FROM ");
        sb.append(tableName);
        sb.append(" WHERE id=");
        sb.append(id.getValue());

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
