package com.example.shiftsix;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shiftsix.containers.Event;
import com.example.shiftsix.databinding.FragmentHomeBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    IFragmentChangeListener fragmentChangeListener;
    RecyclerView recyclerView;
    List<Event> eventList;
    CardRecyclerViewAdapter adapter;

    //HashMap<CardView, CardView> cardList; // contains default card, expanded card - in that order

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof IFragmentChangeListener) {
            fragmentChangeListener = (IFragmentChangeListener) context;
        } else {
            throw new ClassCastException(context.toString()
            + " must implement IFragmentChangeListener");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(getLayoutInflater());

        initRecyclerView();

        /*FloatingActionButton addItemButton = binding.addItemButton;

        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentChangeListener.changeFragment(new SettingsFragment());
            }
        });*/

        /*CalendarView homeCalendar = findViewById(R.id.HomeCalendar);
        homeCalendar.setMinDate(homeCalendar.getDate());
        homeCalendar.setMaxDate(homeCalendar.getDate()+7);*/
        return binding.getRoot();
    }

    private void initRecyclerView() {
        eventList = new ArrayList<Event>();

        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));


        Event[] test = { new Event("name","test desc",new GregorianCalendar()), new Event("name1","test desc1",new GregorianCalendar()), new Event("name3","test desc3",new GregorianCalendar())};
        eventList = new ArrayList<>();

        Collections.addAll(eventList, test);
        adapter = new CardRecyclerViewAdapter(eventList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}