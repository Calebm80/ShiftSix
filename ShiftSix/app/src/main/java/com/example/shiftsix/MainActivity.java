package com.example.shiftsix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
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


public class MainActivity extends AppCompatActivity implements IFragmentChangeListener, IEventListUpdateListener, IPreferenceUpdateListener, INotificationScheduleListener {
    private FragmentManager fragmentManager;
    private List<Event> eventList;
    private ActivityMainBinding binding;
    private SharedPreferences sharedPreferences;
    public static final String CHANNEL_ID = "channel-id";
    private int requestCode = 0;

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
        createNotificationChannel();

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

    @Override
    public void scheduleNotification(String content, long delay) {
        scheduleNotification(createNotification(content), delay);
    }

    ///////////////////////////////
    private void createNotificationChannel() {
        CharSequence name = "ShiftSix-Notifications";
        String description = "Notification channel for the ShiftSix application";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, importance);
        notificationChannel.setDescription(description);
        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(notificationChannel);
    }

    private void scheduleNotification(Notification notification, long delay) {
        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, requestCode, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        requestCode++;

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
    }

    /* creates a reminder about the given event at time/date contained in calendar
     *  with body of String text */
    public Notification createNotification(String content) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        return builder.build();
    }
}