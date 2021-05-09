package com.example.shiftsix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
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


public class MainActivity extends AppCompatActivity implements IFragmentChangeListener, IEventListUpdateListener, IPreferenceUpdateListener {
    private FragmentManager fragmentManager;
    List<Event> eventList;
    ActivityMainBinding binding;
    SharedPreferences sharedPreferences;

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
        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        fragmentManager = getSupportFragmentManager();

        loadEventList();
        loadPreferences();
        initBottomNav();

        //clearEventList();
        //populateTestList();

        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_content_frame, new HomeFragment(eventList), null)
                    .commit();
        }
    }

    private void initBottomNav() {
        BottomNavigationView bottomNav = binding.bottomNav;

        // init home button
        View homeButton = binding.bottomNav.findViewById(R.id.nav_home);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomNav.setSelectedItemId(homeButton.getId());
                changeFragment(new HomeFragment(eventList));
                initBottomNav();
            }
        });

        // init settings button
        View settingsButton = binding.bottomNav.findViewById(R.id.nav_settings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomNav.setSelectedItemId(settingsButton.getId());
                changeFragment(new SettingsFragment(sharedPreferences));
                initBottomNav();
            }
        });

        // init calendar button
        View calendarButton = binding.bottomNav.findViewById(R.id.nav_calendar);
        if (sharedPreferences.getBoolean("calendarMenuVisibility", true)) {
            calendarButton.setVisibility(View.VISIBLE);
            calendarButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottomNav.setSelectedItemId(calendarButton.getId());
                    changeFragment(new CalendarFragment(eventList));
                    initBottomNav();
                }
            });
        } else {
            calendarButton.setVisibility(View.GONE);
        }

        // init expense button
        View expenseButton = binding.bottomNav.findViewById(R.id.nav_expenses);
        if (sharedPreferences.getBoolean("expenseMenuVisibility", true)) {
            expenseButton.setVisibility(View.VISIBLE);
            expenseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottomNav.setSelectedItemId(expenseButton.getId());
                    initBottomNav();
                }
            });
        } else {
            expenseButton.setVisibility(View.GONE);
        }
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

        Context context = MainActivity.this;
        if (context == null) return;

        try {
            FileOutputStream fos = context.openFileOutput("eventList", Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(eventList);
            os.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadPreferences() {
        this.sharedPreferences = MainActivity.this.getPreferences(Context.MODE_PRIVATE);
    }

    @Override
    public void preferenceUpdate() {
        initBottomNav();
    }

    private void populateTestList() {
        for (int i = 0; i < 5; i++) {
            for (int j = 1; j < 12; j++) {
                for (int k = 1; k < 30; k++) {
                    String name = "example event " + String.valueOf(i) + String.valueOf(j) + String.valueOf(k);
                    GregorianCalendar calendar = new GregorianCalendar(i + 2020, j, k);
                    addEvent(new Event(name, "test description", calendar));
                }
            }
        }
    }

    private void clearEventList() {
        for (int i = 0; i <= eventList.size(); i++) {
            removeEvent(eventList.get(i));
        }
    }
}