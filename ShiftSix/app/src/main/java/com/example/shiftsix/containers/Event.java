package com.example.shiftsix.containers;

import com.applandeo.materialcalendarview.EventDay;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Event implements Comparable<Event>, Serializable {
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

    public String getDescription() {
        return description;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public String getDateString() { // example return String: 4/20/2021
        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH) + 1; // month is stored from 0-11 so adjust +1 for final display
        int day_of_month = date.get(Calendar.DAY_OF_MONTH);
        return String.valueOf(month) + '/' + String.valueOf(day_of_month) + '/' + year;
    }

    public String getTimeString() {
        int hour = date.get(Calendar.HOUR);
        int minute = date.get(Calendar.MINUTE);

        String AM_PM = "PM";
        if (date.get(Calendar.AM_PM) == Calendar.AM) AM_PM = "AM";

        String hour_fixed = String.valueOf(hour);
        if (hour == 0) hour_fixed = "12";

        String minute_fixed = String.valueOf(minute);
        while (minute_fixed.length() < 2) {
            minute_fixed = "0" + minute_fixed;
        }

        return hour_fixed + ":" + minute_fixed + ' ' + AM_PM;
    }

    @Override
    public int compareTo(Event event) {
        GregorianCalendar x = this.date;
        int year_x = x.get(Calendar.YEAR);
        int month_x = x.get(Calendar.MONTH);
        int day_of_month_x = x.get(Calendar.DAY_OF_MONTH);
        int hour_x = x.get(Calendar.HOUR_OF_DAY);
        int minute_x = x.get(Calendar.MINUTE);

        GregorianCalendar y = event.getDate();
        int year_y = y.get(Calendar.YEAR);
        int month_y = y.get(Calendar.MONTH);
        int day_of_month_y = y.get(Calendar.DAY_OF_MONTH);
        int hour_y = y.get(Calendar.HOUR_OF_DAY);
        int minute_y = y.get(Calendar.MINUTE);

        if (year_x != year_y) return Integer.compare(year_x, year_y);
        if (month_x != month_y) return Integer.compare(month_x, month_y);
        if (day_of_month_x != day_of_month_y)
            return Integer.compare(day_of_month_x, day_of_month_y);
        if (hour_x != hour_y) return Integer.compare(hour_x, hour_y);
        if (minute_x != minute_y) return Integer.compare(minute_x, minute_y);
        return 0;
    }
}
