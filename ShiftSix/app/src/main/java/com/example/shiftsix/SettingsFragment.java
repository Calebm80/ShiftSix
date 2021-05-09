package com.example.shiftsix;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.example.shiftsix.containers.Preference;
import com.example.shiftsix.databinding.FragmentHomeBinding;
import com.example.shiftsix.databinding.FragmentSettingsBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.Objects;


public class SettingsFragment extends Fragment {
    FragmentSettingsBinding binding;
    SharedPreferences sharedPreferences;

    public SettingsFragment(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSettingsBinding.inflate(getLayoutInflater());
        initMenuSettings();

        return binding.getRoot();
    }

    private void initMenuSettings() {
        SwitchMaterial expenseMenuSwitch = createNewSwitch(
                new Preference(
                        "Expense Menu Visibility",
                        R.id.setting_expense_menu_visibility,
                        1
                )
        );
        SwitchMaterial calendarMenuSwitch = createNewSwitch(
                new Preference(
                        "Calendar Menu Visibility",
                        R.id.setting_calendar_menu_visibility,
                        1
                )
        );
        binding.menuSettings.addView(expenseMenuSwitch);
        binding.menuSettings.addView(calendarMenuSwitch);
    }

    private SwitchMaterial createNewSwitch(Preference preference) {
        preference.setValue(getSavedPreference(preference));

        SwitchMaterial switchMaterial = new SwitchMaterial(this.getContext()); // warning that context might be null but it makes no sense - fragment would not be initialized if there is no context ??
        switchMaterial.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        switchMaterial.setText(preference.getName());
        switchMaterial.setId(preference.getId());
        if (preference.getValue() != 0) {
            switchMaterial.setChecked(true);
        }
        switchMaterial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    System.out.println(buttonView.getId() + " CHECKED");
                    updatePreference(preference.getId(), 1);
                } else {
                    System.out.println(buttonView.getId() + " UNCHECKED");
                    updatePreference(preference.getId(), 0);
                }
            }
        });
        return switchMaterial;
    }

    private void updatePreference(int key, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(String.valueOf(key), value);
        editor.apply(); // ASYNCHRONOUS - USE commit() FOR SYNCHRONOUS
    }

    private int getSavedPreference(Preference preference) {
        return sharedPreferences.getInt(preference.getIdAsString(), preference.getValue());
    }
}