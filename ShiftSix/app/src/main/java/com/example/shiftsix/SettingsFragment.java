package com.example.shiftsix;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.shiftsix.databinding.FragmentHomeBinding;
import com.example.shiftsix.databinding.FragmentSettingsBinding;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.Objects;


public class SettingsFragment extends Fragment {
    FragmentSettingsBinding binding;

    public SettingsFragment() {
        // Required empty public constructor
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
        SwitchMaterial switchMaterial = createNewSwitch("menu test");
        binding.menuSettings.addView(switchMaterial);
    }

    private SwitchMaterial createNewSwitch(String text) {
        SwitchMaterial switchMaterial = new SwitchMaterial(this.getContext()); // warning that context might be null but it makes no sense - fragment would not be initialized if there is not context ??
        switchMaterial.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        switchMaterial.setText(text);
        return switchMaterial;
    }
}