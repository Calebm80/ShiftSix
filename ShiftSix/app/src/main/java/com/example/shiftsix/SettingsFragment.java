package com.example.shiftsix;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.shiftsix.databinding.FragmentSettingsBinding;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class SettingsFragment extends Fragment {
    FragmentSettingsBinding binding;
    SharedPreferences sharedPreferences;
    IPreferenceUpdateListener preferenceUpdateListener;

    public SettingsFragment(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof IPreferenceUpdateListener) {
            preferenceUpdateListener = (IPreferenceUpdateListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement IPreferenceUpdateListener");
        }
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
        initSwitches();
    }

    private void initSwitches() {
        SwitchMaterial switchMaterial = binding.calendarMenuVisibility;
        initSwitch(switchMaterial, "calendarMenuVisibility");

        switchMaterial = binding.expenseMenuVisibility;
        initSwitch(switchMaterial, "expenseMenuVisibility");
    }

    private void initSwitch(SwitchMaterial switchMaterial, String key) {
        switchMaterial.setChecked(sharedPreferences.getBoolean(key, true));

        switchMaterial.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    updatePreference(key, true);
                } else {
                    updatePreference(key, false);
                }
                preferenceUpdateListener.preferenceUpdate();
            }
        });
    }

    private void updatePreference(String key, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply(); // ASYNCHRONOUS - USE commit() FOR SYNCHRONOUS
    }

    private void updatePreference(String key, boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply(); // ASYNCHRONOUS - USE commit() FOR SYNCHRONOUS
    }
}