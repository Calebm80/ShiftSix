package com.example.shiftsix;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shiftsix.containers.Event;

import java.util.List;

public class CardRecyclerViewAdapter extends RecyclerView.Adapter<CardRecyclerViewAdapter.ViewHolder> {
    private List<Event> data;

    CardRecyclerViewAdapter (List<Event> data) {
        this.data = data;
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
    }


    @Override
    public int getItemCount() {
        return this.data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView cardName;
        private TextView cardDate;

        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            this.cardName = view.findViewById(R.id.card_name);
            this.cardDate = view.findViewById(R.id.card_date);
        }

        @Override
        public void onClick(View view) {
            System.out.println("click");
        }
    }

        /*@Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "position : " + getLayoutPosition() + " text : " + this.textView.getText(), Toast.LENGTH_SHORT).show();
        }*/
}
