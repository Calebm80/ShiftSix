package com.example.shiftsix;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shiftsix.containers.Event;

import java.util.List;

public class CardRecyclerViewAdapter extends RecyclerView.Adapter<CardRecyclerViewAdapter.ViewHolder> {
    private onItemClickListener onItemClickListener;
    private List<Event> data;

    CardRecyclerViewAdapter (List<Event> data, onItemClickListener onItemClickListener) {
        this.data = data;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public CardRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_row_item, parent, false);
        return new ViewHolder(rowItem);
    }

    @Override
    public void onBindViewHolder(CardRecyclerViewAdapter.ViewHolder holder, int position) {
        Event event = this.data.get(position);
        String name = event.getName();
        String dateString = event.getDateString();
        holder.cardName.setText(name);
        holder.cardDate.setText(dateString);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(event);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.data.size();
    }

    public interface onItemClickListener {
        void onItemClick(Event event);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView cardName;
        private TextView cardDate;

        public ViewHolder(View view) {
            super(view);
            this.cardName = view.findViewById(R.id.card_name);
            this.cardDate = view.findViewById(R.id.card_date);
        }

        /*@Override
        public void onClick(View view) {
            System.out.println("CLICKED");
            Bundle bundle = new Bundle();
            bundle.putString("name", cardName.getText().toString());
            bundle.putString("date", cardDate.getText().toString());
            SelectedEventFragment selectedEventFragment = new SelectedEventFragment();
            selectedEventFragment.setArguments(bundle);
            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            activity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_content_frame, selectedEventFragment)
                    .addToBackStack(null)
                    .commit();
        }*/
    }
}
