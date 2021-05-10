package com.example.shiftsix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

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

    /* replaces all fragments in the FragmentManager with the given fragment */
    @Override
    public void changeFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        for (Fragment frag : fragmentManager.getFragments()) {
            transaction.remove(frag);
        }

        transaction.add(R.id.fragment_content_frame, fragment, null)
                .commit();
    }

    /* Adds a fragment to the FragmentManager without deleting or disabling any current fragments
    *  In the future this should disable background fragments so that click listeners do not allow
    *  clicking through windows, until then it is not safe to use */
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

    /* Initializes the bottom navigation view, should be recalled any time bottomNav state is changed
    *   in order to properly consider activation settings in SharedPreferences */
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
                    changeFragment(new ExpenseFragment());
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

    /* Sorts eventList and prompts all active recycler views to redraw */
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

    /* populates the eventList with slightly random dates in the year 2021, ensuring at least 8 dates
    *  in each month. Dates may not be unique. All times will be midnight. Prompts a redraw of RecyclerView. */
    private void populateTestList() {
        for (int month = 0; month < 12; month++) {
            for (int i = 0; i < 8; i++) {
                int day = (int) (Math.random()*30) + 1;
                String name = "example event " + String.valueOf(i) + String.valueOf(month);
                GregorianCalendar calendar = new GregorianCalendar(2021, month, day);
                eventList.add(new Event(name, "test description", calendar));
            }
        }
        updateRecyclerView();
        saveEventList();
    }

    /* Clears all events and prompts a redraw of RecyclerView */
    private void clearEventList() {
        eventList.clear();
        updateRecyclerView();
        saveEventList();
    }
}