package com.example.musclerent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

class SalleViewHolder extends RecyclerView.ViewHolder {
    private final TextView salleItemView;

    private SalleViewHolder(View itemView) {
        super(itemView);
        salleItemView = itemView.findViewById(R.id.textViewSalle);
    }

    public void bind(String text) {
        salleItemView.setText(text);
    }

    static SalleViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_salle, parent, false);
        return new SalleViewHolder(view);
    }
}
