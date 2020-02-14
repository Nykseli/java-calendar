package com.oop.java.calendar.data.db;

public class Value<T> {

    private T value;
    private String rowName;

    public Value(T value, String rowName) {
        this.value = value;
        this.rowName = rowName;
    }

    public Value(String rowName) {
        this.rowName = rowName;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getRowName() {
        return rowName;
    }

    public void setRowName(String rowName) {
        this.rowName = rowName;
    }
}
