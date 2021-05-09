package com.example.shiftsix;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.applandeo.materialcalendarview.CalendarUtils;
import com.applandeo.materialcalendarview.EventDay;
import com.example.shiftsix.containers.Event;
import com.example.shiftsix.databinding.FragmentCalendarBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarFragment extends Fragment {
    FragmentCalendarBinding binding;
    List<Event> eventList;
    List<EventDay> bufferedEvents;

    public CalendarFragment(List<Event> eventList) {
        this.eventList = eventList;
        bufferedEvents = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCalendarBinding.inflate(getLayoutInflater());
        initCalendar();
        return binding.getRoot();
    }

    private void initCalendar() {
        int selected_month = binding.homeCalendar.getCurrentPageDate().get(Calendar.MONTH);
        for (Event event : eventList) {
            int m_dif = event.getDate().get(Calendar.MONTH) - selected_month;
            if (m_dif > -3 && m_dif < 3) {
                bufferedEvents.add(new EventDay(event.getDate(), R.drawable.ic_add_circle_button));
            }
        }
        binding.homeCalendar.setEvents(bufferedEvents);
    }
}