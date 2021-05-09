package com.example.shiftsix;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.example.shiftsix.databinding.FragmentExpenseBinding;
import com.example.shiftsix.databinding.FragmentSettingsBinding;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class ExpenseFragment extends Fragment {
    FragmentExpenseBinding binding;

    public ExpenseFragment() {
        // required empty constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentExpenseBinding.inflate(getLayoutInflater());

        return binding.getRoot();
    }
}