package com.example.musclerent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

class ReservationViewHolder extends RecyclerView.ViewHolder {
    private final TextView reservationItemView;

    private ReservationViewHolder(View itemView) {
        super(itemView);
        reservationItemView = itemView.findViewById(R.id.textView);
    }

    public void bind(String text) {
        reservationItemView.setText(text);
    }

    static ReservationViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new ReservationViewHolder(view);
    }
}
