package com.oop.java.calendar.data.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Singleton class for sqlite database connection
 */
public class DatabaseHelper {
    private static DatabaseHelper instance = new DatabaseHelper();

    Connection connection;

    private DatabaseHelper() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:calendar.db");
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            System.err.println("Database connection failed.");
            System.exit(1);
        }
    }

    public static DatabaseHelper getInstance() {
        return instance;
    }

    public void execute(String cmd) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(cmd);
    }

    /**
     * Safely execute sql query.
     *
     * This prevents sql injections
     */
    public void safeExecute(String statement, Value<?>[] values) throws SQLException {

        PreparedStatement stmt = connection.prepareStatement(statement);
        // statements need to start from 1
        for (int i = 0; i < values.length; i++) {
            // Boolean Values doesn't translate to string very well
            if (values[i].getValue() instanceof Boolean)
                stmt.setBoolean(i + 1, (Boolean) values[i].getValue());
            else
                stmt.setString(i + 1, values[i].getValue().toString());
        }

        stmt.executeUpdate();
    }

    public ResultSet query(String cmd) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(cmd);
    }

    /**
     * Wrapper for rs.getInt try / catch
     *
     * @param rs
     * @param name
     * @return Integer | null if value is not found
     */
    public static Integer getInteger(ResultSet rs, String name) {
        try {
            return rs.getInt(name);
        } catch (SQLException e) {
        }

        return null;
    }

    /**
     * Wrapper for rs.getString try / catch
     *
     * @param rs
     * @param name
     * @return String | null if value is not found
     */
    public static String getString(ResultSet rs, String name) {
        try {
            return rs.getString(name);
        } catch (SQLException e) {
        }

        return null;
    }

    /**
     * Wrapper for rs.getString try / catch
     *
     * @param rs
     * @param name
     * @return Boolean | null if value is not found
     */
    public static Boolean getBoolean(ResultSet rs, String name) {
        try {
            return rs.getBoolean(name);
        } catch (SQLException e) {
        }

        return null;
    }

}
