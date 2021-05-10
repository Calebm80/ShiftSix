package com.example.shiftsix.containers;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.view.ContextThemeWrapper;

import androidx.core.app.NotificationCompat;

import com.applandeo.materialcalendarview.EventDay;
import com.example.shiftsix.R;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static androidx.core.content.ContextCompat.getSystemService;

public class Event implements Comparable<Event>, Serializable {
    private String name;
    private String description;
    private GregorianCalendar date;

    public Event(String name, String description, GregorianCalendar date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

    /* initializes an event with null name and description, current date/time */
    public Event() {
        this.name = null;
        this.description = null;
        this.date = new GregorianCalendar();
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

    /* returns time as string -> m/d/yyyy*/
    public String getDateString() {
        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH); // month is stored from 0-11 so adjust +1 for final display
        int day_of_month = date.get(Calendar.DAY_OF_MONTH);
        return String.valueOf(month + 1) + '/' + String.valueOf(day_of_month) + '/' + year;
    }

    /* returns time as string -> h:mm AM/PM */
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

    /* returns true if given event is before this event */
    public Boolean before(Event event) {
        int result = this.compareTo(event);
        if (result < 0) return true;
        return false;
    }

    /* returns true if given event is after this event */
    public Boolean after(Event event) {
        int result = this.compareTo(event);
        return result > 0;
    }

    /* returns true if given is on the same day as this event */
    public Boolean sameDay(Event event) {
        int year_given = event.getDate().get(Calendar.YEAR);
        int month_given = event.getDate().get(Calendar.MONTH);
        int day_given = event.getDate().get(Calendar.DATE);

        int year = this.getDate().get(Calendar.YEAR);
        int month = this.getDate().get(Calendar.MONTH);
        int day = this.getDate().get(Calendar.DATE);

        return (year == year_given) && (month == month_given) && (day == day_given);
    }
}
