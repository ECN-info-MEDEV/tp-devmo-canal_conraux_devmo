package com.example.musclerent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Classe qui permet de créer un pattern facilitant l'affichage des salles
 *
 */
class SalleViewHolder extends RecyclerView.ViewHolder {
    private final TextView salleItemView;

    private SalleViewHolder(View itemView) {
        super(itemView);
        salleItemView = itemView.findViewById(R.id.textViewSalle);
    }

    public void bind(String text) {
        salleItemView.setText(text);
    }


    /**
     * Méthode qui attache et return le view hodler à une fenêtre parente
     * @param parent le layout parent
     * @return
     */
    static SalleViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_salle, parent, false);
        return new SalleViewHolder(view);
    }
}
