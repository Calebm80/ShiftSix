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

import com.example.shiftsix.databinding.FragmentExpenseBinding;
import com.example.shiftsix.databinding.FragmentSettingsBinding;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class ExpenseFragment extends Fragment {
    FragmentExpenseBinding binding;
    IFragmentChangeListener fragmentChangeListener;

    public ExpenseFragment() {
        // required empty constructor
    }

    // callback necessary to tell main activity to change fragments from within a fragment - ignorable code, just gets us the listener
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof IFragmentChangeListener) {
            this.fragmentChangeListener = (IFragmentChangeListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement IFragmentChangeListener");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentExpenseBinding.inflate(getLayoutInflater());
        initShoppingButton();
        return binding.getRoot();
    }

    private void initShoppingButton() {
        binding.shoppingListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentChangeListener.changeFragment(new ShoppingFragment());
            }
        });
    }
}