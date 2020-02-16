package com.oop.java.calendar.data.providers;

import java.util.ArrayList;

abstract class AbstractProvider<V> {
    protected ArrayList<V> views = new ArrayList<>();

    public void addView(V view) {
        views.add(view);
    }

    public void deleteView(V view) {
        views.remove(view);
    }

    protected abstract void notifyListeners();
}
