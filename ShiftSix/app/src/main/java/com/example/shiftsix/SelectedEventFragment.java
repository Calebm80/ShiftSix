package com.example.shiftsix;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shiftsix.containers.Event;
import com.example.shiftsix.databinding.FragmentHomeBinding;
import com.example.shiftsix.databinding.FragmentSelectedEventBinding;

public class SelectedEventFragment extends Fragment {
    FragmentSelectedEventBinding binding;
    Event event;

    public SelectedEventFragment(Event event) {
        this.event = event;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSelectedEventBinding.inflate(getLayoutInflater());
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_selected_event, container, false);
    }
}