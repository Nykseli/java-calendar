package com.oop.java.calendar.data.db;

/**
 * Value is generic class used for database values for database models.
 */
public class Value<T> {

    private T value;
    private T originalValue;

    private String columnName;

    public Value(T value, String columnName) {
        this.value = value;
        this.originalValue = value;
        this.columnName = columnName;
    }

    public Value(String columnName) {
        this.columnName = columnName;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        if (originalValue == null)
            this.originalValue = value;

        this.value = value;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    /**
     * Is Value modified after initialization
     *
     * @return true if value is modified after initialization
     */
    public boolean isModified() {
        if (value == null)
            return false;
        // If value is not null and orignal value is, the value is changed
        if (originalValue == null)
            return true;

        // If neither is null, check equality
        // If they are equal, value is not modified
        return !originalValue.equals(value);
    }
}
