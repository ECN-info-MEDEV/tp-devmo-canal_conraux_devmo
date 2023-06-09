package com.example.musclerent;

import android.app.Application;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Classe qui permet de créer un pattern facilitant l'affichage des reservation
 *
 */
class ReservationViewHolder extends RecyclerView.ViewHolder {
    private final TextView reservationItemView;
    private final ImageButton deleteButton;
    private final ImageButton infoButton;


    private ReservationViewHolder(View itemView) {
        super(itemView);
        reservationItemView = itemView.findViewById(R.id.textView);
        deleteButton = itemView.findViewById(R.id.delete_button);
        infoButton = itemView.findViewById(R.id.info_button);
    }

    public void bind(String text) {
        reservationItemView.setText(text);
    }
    public void deleteGiveTagId(String id) {deleteButton.setTag(id);}
    public void infoGiveTagId(String id) {infoButton.setTag(id);}

    /**
     * Méthode qui attache et return le view hodler à une fenêtre parente
     * @param parent le layout parent
     * @return
     */
    static ReservationViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new ReservationViewHolder(view);
    }
}
