package com.example.databasebabysteps.components;

import androidx.annotation.NonNull;

public class Note {

    // Attributes
    private Long id;
    private String note;

    // Methods

    @NonNull
    @Override
    public String toString() {
        return note;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public String getNote() {
        return note;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
