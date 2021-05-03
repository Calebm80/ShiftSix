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
        holder.cardName.setText(event.getName());
        holder.cardDate.setText(event.getDateString());
        holder.cardTime.setText(event.getTimeString());

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
        private TextView cardTime;

        public ViewHolder(View view) {
            super(view);
            this.cardName = view.findViewById(R.id.card_name);
            this.cardDate = view.findViewById(R.id.card_date);
            this.cardTime = view.findViewById(R.id.card_time);
        }
    }
}
