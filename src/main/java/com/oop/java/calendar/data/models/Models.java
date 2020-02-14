package com.oop.java.calendar.data.models;

/**
 * Models class contains the list of models used in database.
 *
 * Model class needs to be added here or it cannot be used in the application
 */
public class Models {
    private static final Model[] MODELS = {
            // Initialize the Task model
            new Task()
            //
    };

    /**
     * Call once in start up to initatilze databalses
     */
    public static void initializeModels() {
        for (Model m : MODELS) {
            m.initialize();
        }
    }
}
