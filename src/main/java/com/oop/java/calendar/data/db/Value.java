package com.oop.java.calendar.data.db;

public class Value<T> {

    private T value;
    private T originalValue;

    private String rowName;

    public Value(T value, String rowName) {
        this.value = value;
        this.originalValue = value;
        this.rowName = rowName;
    }

    public Value(String rowName) {
        this.rowName = rowName;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        if (originalValue == null)
            this.originalValue = value;

        this.value = value;
    }

    public String getRowName() {
        return rowName;
    }

    public void setRowName(String rowName) {
        this.rowName = rowName;
    }

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
