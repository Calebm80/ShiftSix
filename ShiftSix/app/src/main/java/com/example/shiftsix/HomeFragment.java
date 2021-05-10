package com.example.shiftsix;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shiftsix.containers.Event;
import com.example.shiftsix.databinding.FragmentHomeBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class HomeFragment extends Fragment {
    FragmentHomeBinding binding;
    IFragmentChangeListener fragmentChangeListener;
    RecyclerView recyclerView;
    List<Event> eventList;
    CardRecyclerViewAdapter adapter;

    public HomeFragment(List<Event> eventList) {
        this.eventList = eventList;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof IFragmentChangeListener) {
            fragmentChangeListener = (IFragmentChangeListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement IFragmentChangeListener");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(getLayoutInflater());

        initRecyclerView();

        FloatingActionButton addItemButton = binding.addItemButton;
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentChangeListener.changeFragment(new AddEventFragment());
            }
        });

        return binding.getRoot();
    }

    private void initRecyclerView() {
        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        adapter = new CardRecyclerViewAdapter(eventList, new CardRecyclerViewAdapter.onItemClickListener() {
            @Override
            public void onItemClick(Event event) {
                fragmentChangeListener.changeFragment(new SelectedEventFragment(event, eventList));
            }
        });
        recyclerView.setAdapter(adapter);
    }

    /* prompts a resort and redraw from the recyclerview */
    public void updateRecyclerView() {
        if (recyclerView.getAdapter() != null)
            recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}