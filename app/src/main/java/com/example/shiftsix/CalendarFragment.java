package com.example.shiftsix;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applandeo.materialcalendarview.CalendarUtils;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnCalendarPageChangeListener;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.example.shiftsix.containers.Event;
import com.example.shiftsix.databinding.FragmentCalendarBinding;

import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class CalendarFragment extends Fragment {
    FragmentCalendarBinding binding;
    List<Event> eventList;
    List<EventDay> bufferedEvents;

    public CalendarFragment(List<Event> eventList) {
        this.eventList = eventList;
        this.bufferedEvents = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCalendarBinding.inflate(getLayoutInflater());
        initCalendar();
        initClickListener();
        return binding.getRoot();
    }

    private void initCalendar() {
        /* this method is very bad - alternative would be making Event class extend eventDay class.
        *  That wouldn't work at the moment, since eventDay class is not serializable. Fixing this
        *  would allow much better handling of events and proper calendar user interaction */

        for (Event event : eventList) {
            bufferedEvents.add(new EventDay(event.getDate(), R.drawable.ic_baseline_error_outline_24));
        }
        binding.homeCalendar.setEvents(bufferedEvents);
    }

    private void initClickListener() {
        binding.homeCalendar.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {
                Calendar clickedDay = eventDay.getCalendar();
                GregorianCalendar day = calendarToGregorian(clickedDay);
                Event event = null;
                int i = 0;
                do {
                    event = eventList.get(i);
                    i++;
                } while (
                        event.getDate().get(Calendar.YEAR) <= day.get(Calendar.YEAR)
                        && event.getDate().get(Calendar.MONTH) <= day.get(Calendar.MONTH)
                        && event.getDate().get(Calendar.DAY_OF_MONTH) <= day.get(Calendar.DAY_OF_MONTH)
                );

                if (event.getDate() == day) {
                    System.out.println(event.getDateString());
                }
                System.out.println("test: " + event.getDate());
                System.out.println("test2: " + day);
            }
        });
    }

    private GregorianCalendar calendarToGregorian(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        return new GregorianCalendar(year, month, day, hourOfDay, minute);
    }
}