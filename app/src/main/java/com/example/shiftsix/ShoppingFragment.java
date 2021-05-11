package com.example.shiftsix;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shiftsix.databinding.FragmentShoppingBinding;

public class ShoppingFragment extends Fragment {
    FragmentShoppingBinding binding;
    public ShoppingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.binding = FragmentShoppingBinding.inflate(getLayoutInflater());

        return binding.getRoot();
    }
}