package com.example.shiftsix;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shiftsix.containers.Event;
import com.example.shiftsix.databinding.FragmentSelectedEventBinding;

import java.util.List;

public class SelectedEventFragment extends Fragment {
    FragmentSelectedEventBinding binding;
    IEventListUpdateListener eventListUpdateListener;
    IFragmentChangeListener fragmentChangeListener;
    Event event;
    List<Event> eventList;

    public SelectedEventFragment(Event event, List<Event> eventList) {
        this.event = event;
        this.eventList = eventList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSelectedEventBinding.inflate(getLayoutInflater());
        initFragment();
        initDeleteButton();
        return binding.getRoot();
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

        if (context instanceof IFragmentChangeListener) {
            fragmentChangeListener = (IFragmentChangeListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement IFragmentChangeListener");
        }
    }

    private void initFragment() {
        binding.eventName.setText(event.getName());
        binding.eventDate.setText(event.getDateString());
        binding.eventTime.setText(event.getTimeString());
        binding.eventDescription.setText(event.getDescription());
        //binding.checkboxEventReminder;
    }

    private void initDeleteButton() {
        binding.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("DELETE");
                eventListUpdateListener.removeEvent(event);
                fragmentChangeListener.changeFragment(new HomeFragment(eventList));
            }
        });
    }

}