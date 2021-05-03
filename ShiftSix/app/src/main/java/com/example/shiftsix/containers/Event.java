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

    public String getDateString() { // example return String: 4/20/2021
        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH);
        int day_of_month = date.get(Calendar.DAY_OF_MONTH);
        return String.valueOf(month) + '/' + String.valueOf(day_of_month) + '/' + year;
    }

    public String getTimeString() {
        int hour = date.get(Calendar.HOUR_OF_DAY);
        int minute = date.get(Calendar.MINUTE);

        return String.valueOf(hour) + ":" + String.valueOf(minute) + date.get(Calendar.AM_PM);
    }
}
