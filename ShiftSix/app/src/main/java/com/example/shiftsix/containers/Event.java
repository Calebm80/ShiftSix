package com.example.shiftsix.containers;

import androidx.cardview.widget.CardView;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Event {
    private String name;
    private String description;
    private GregorianCalendar date;

    public Event(String name, String description, GregorianCalendar date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    String getDescription() {
        return description;
    }

    public GregorianCalendar getDate() {
        return date;
    }
}
