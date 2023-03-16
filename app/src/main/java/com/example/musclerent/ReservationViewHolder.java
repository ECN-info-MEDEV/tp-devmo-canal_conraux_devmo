package com.example.musclerent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

class ReservationViewHolder extends RecyclerView.ViewHolder {
    private final TextView reservationItemView;
    private final ImageButton deleteButton;

    private ReservationViewHolder(View itemView) {
        super(itemView);
        reservationItemView = itemView.findViewById(R.id.textView);
        deleteButton = itemView.findViewById(R.id.delete_button);
    }

    public void bind(String text) {
        reservationItemView.setText(text);
    }
    public void deleteGiveTagId(String id) {deleteButton.setTag(id);}

    static ReservationViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new ReservationViewHolder(view);
    }
}
