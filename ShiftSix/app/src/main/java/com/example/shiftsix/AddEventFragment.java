package com.example.shiftsix;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shiftsix.containers.Event;
import com.example.shiftsix.databinding.FragmentAddEventBinding;
import com.google.android.material.button.MaterialButton;

import java.util.GregorianCalendar;

import static androidx.core.content.ContextCompat.getSystemService;


public class AddEventFragment extends Fragment {
    private IEventListUpdateListener eventListUpdateListener;
    private INotificationScheduleListener notificationScheduleListener;
    private FragmentAddEventBinding binding;


    public AddEventFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof IEventListUpdateListener) {
            eventListUpdateListener = (IEventListUpdateListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement IEventListUpdateListener");
        }

        if (context instanceof  INotificationScheduleListener) {
            notificationScheduleListener = (INotificationScheduleListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement INotificationScheduleListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddEventBinding.inflate(getLayoutInflater());
        initSubmitButton();
        return binding.getRoot();
    }

    private void initSubmitButton() {
        MaterialButton submitButton = binding.submitButton;
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createEvent();
            }
        });
    }

    /* creates a new event, adds it to the RecyclerView and the eventList,
    *  prompts RecyclerView to redraw */
    private void createEvent() {
        String eventName = binding.eventName.getText().toString();
        String eventDate = binding.eventDate.getText().toString();
        String eventTime = binding.eventTime.getText().toString();
        String eventDescription = binding.eventDescription.getText().toString();
        GregorianCalendar date = parseEventDateTime(eventDate, eventTime);

        Event event = new Event(eventName, eventDescription, date);

        notificationScheduleListener.scheduleNotification(eventName + ": " + eventDescription, 5000); // 5 second delay
        eventListUpdateListener.addEvent(event);
    }

    /* this whole function is super unsafe for unusual formats at the moment
    *  eventDate must be given in format -> d/m/yyyy
    *  eventTime must be given in format -> h:mm AM/PM
    *  any other format will cause exceptions that are not handled */
    private GregorianCalendar parseEventDateTime(String eventDate, String eventTime) {
        String[] eventDateSplit = eventDate.split("/");
        String[] eventTimeSplit = eventTime.split(":");
        String[] eventTimeSplitAM_PM = eventTimeSplit[1].split(" ");

        int year = Integer.parseInt(eventDateSplit[2]);
        int month = Integer.parseInt(eventDateSplit[0])-1;
        int day = Integer.parseInt(eventDateSplit[1]);
        int hourOfDay = Integer.parseInt(eventTimeSplit[0]);
        int minute = Integer.parseInt(eventTimeSplitAM_PM[0]);

        if (eventTimeSplitAM_PM[1].equals("PM")) hourOfDay += 12;

        return new GregorianCalendar(year, month, day, hourOfDay, minute);
    }
}