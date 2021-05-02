package com.example.shiftsix;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.shiftsix.databinding.FragmentHomeBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeFragment extends Fragment {
    CardView calendarCard;
    FragmentHomeBinding binding;
    IFragmentChangeListener fragmentChangeListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IFragmentChangeListener) {
            fragmentChangeListener = (IFragmentChangeListener) context;
        } else {
            throw new ClassCastException(context.toString()
            + " must implement IFragmentChangeListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        calendarCard = binding.calendarCard;

        FloatingActionButton addItemButton = binding.addItemButton;

        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentChangeListener.changeFragment(new SettingsFragment());
            }
        });

        /*CalendarView homeCalendar = findViewById(R.id.HomeCalendar);
        homeCalendar.setMinDate(homeCalendar.getDate());
        homeCalendar.setMaxDate(homeCalendar.getDate()+7);*/
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}