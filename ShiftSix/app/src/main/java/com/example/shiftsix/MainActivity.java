package com.example.shiftsix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.shiftsix.containers.Event;
import com.example.shiftsix.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;


public class MainActivity extends AppCompatActivity implements IFragmentChangeListener, IEventListUpdateListener {
    private FragmentManager fragmentManager;
    List<Event> eventList;
    ActivityMainBinding binding;

    @Override
    public void changeFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        for (Fragment frag : fragmentManager.getFragments()) {
            transaction.remove(frag);
        }

        transaction.add(R.id.fragment_content_frame, fragment, null)
                .commit();
    }

    @Override
    public void stackFragment(Fragment fragment) {
        fragmentManager.beginTransaction()
                .add(R.id.fragment_content_frame, fragment, null)
                .commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        fragmentManager = getSupportFragmentManager();
        loadEventList();
        //populateTestList(); - uncomment to re-populate eventList with test values

        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_content_frame, new HomeFragment(eventList), null)
                    .commit();
        }

        BottomNavigationView bottomNav = binding.bottomNav;
        View homeButton = binding.bottomNav.findViewById(R.id.nav_home);
        View settingsButton = binding.bottomNav.findViewById(R.id.nav_settings);
        View calendarButton = binding.bottomNav.findViewById(R.id.nav_calendar);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomNav.setSelectedItemId(homeButton.getId());
                changeFragment(new HomeFragment(eventList));
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomNav.setSelectedItemId(settingsButton.getId());
                changeFragment(new SettingsFragment());
            }
        });

        calendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomNav.setSelectedItemId(calendarButton.getId());
                changeFragment(new CalendarFragment());
            }
        });
    }

    @Override
    public void addEvent(Event event) {
        eventList.add(event);
        updateRecyclerView();
        saveEventList();
    }

    @Override
    public void removeEvent(Event event) {
        eventList.remove(event);
        updateRecyclerView();
        saveEventList();
    }

    private void updateRecyclerView() {
        Collections.sort(eventList);
        for (Fragment fragment : fragmentManager.getFragments()) {
            if (fragment instanceof HomeFragment) {
                ((HomeFragment) fragment).updateRecyclerView();
            }
        }
    }

    private void loadEventList() {
        eventList = new ArrayList<>();

        Context context = MainActivity.this;
        if (context == null) return;

        try {
            FileInputStream fis = context.openFileInput("eventList");
            ObjectInputStream is = new ObjectInputStream(fis);
            eventList = (List<Event>) is.readObject();
            is.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            Toast toast = Toast.makeText(context, "Error loading event list.", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private void saveEventList() {
        if (eventList.isEmpty()) return;
        try {
            Context context = MainActivity.this;
            if (context == null) return;

            FileOutputStream fos = context.openFileOutput("eventList", Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(eventList);
            os.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void populateTestList() {
        for (int i = 0; i < 5; i++) {
            for (int j = 1; j < 12; j++) {
                for (int k = 1; k < 3; k++) {
                    String name = "example event " + String.valueOf(i) + String.valueOf(j) + String.valueOf(k);
                    GregorianCalendar calendar = new GregorianCalendar(i+2020,j+1,k*5);
                    addEvent(new Event(name, "test description", calendar));
                }
            }
        }
    }

    private void clearEventList() {
        for (Event event : eventList) {
            removeEvent(event);
        }
    }
}