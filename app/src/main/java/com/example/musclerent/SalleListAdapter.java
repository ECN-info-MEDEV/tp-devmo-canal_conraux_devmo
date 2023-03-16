package com.example.musclerent;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class SalleListAdapter extends ListAdapter<Salle, SalleViewHolder> {

    public SalleListAdapter(@NonNull DiffUtil.ItemCallback<Salle> diffCallback) {
        super(diffCallback);
    }

    @Override
    public SalleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return SalleViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(SalleViewHolder holder, int position) {
        Salle current = getItem(position);
        String bindingText = "N° salle : " + current.getSalleId() + "\nNom : " + current.getNom() ;
        holder.bind(bindingText);
    }

    static class SalleDiff extends DiffUtil.ItemCallback<Salle> {

        @Override
        public boolean areItemsTheSame(@NonNull Salle oldItem, @NonNull Salle newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Salle oldItem, @NonNull Salle newItem) {
            return oldItem.getSalleId() == newItem.getSalleId();
        }
    }
}
