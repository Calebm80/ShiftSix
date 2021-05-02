package com.example.shiftsix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.shiftsix.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IFragmentChangeListener {
    private FragmentManager fragmentManager;

    @Override
    public void changeFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment oldFragment = fragmentManager.findFragmentById(R.id.fragment_content_frame);

        if (oldFragment != null)
            transaction.remove(oldFragment);

        transaction.add(R.id.fragment_content_frame, fragment, null)
                .commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        fragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_content_frame, HomeFragment.class, null)
                    .commit();
        }

        BottomNavigationView bottomNav = binding.bottomNav;
        View homeButton = binding.bottomNav.findViewById(R.id.nav_home);
        View calendarButton = binding.bottomNav.findViewById(R.id.nav_calendar);
        View settingsButton = binding.bottomNav.findViewById(R.id.nav_settings);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(new HomeFragment());
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(new SettingsFragment());
            }
        });

        /*CalendarView homeCalendar = findViewById(R.id.HomeCalendar);
        homeCalendar.setMinDate(homeCalendar.getDate());
        homeCalendar.setMaxDate(homeCalendar.getDate()+7);*/
    }
}