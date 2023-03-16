package com.example.musclerent;

import android.app.Application;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class ReservationListAdapter extends ListAdapter<Reservation, ReservationViewHolder> {

    public ReservationListAdapter(@NonNull DiffUtil.ItemCallback<Reservation> diffCallback) {
        super(diffCallback);
    }

    @Override
    public ReservationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ReservationViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ReservationViewHolder holder, int position) {
        Reservation current = getItem(position);
        String bindingText = "Salle : ... \n" + "Adresse : ... \n" + "Date : " + current.getDate() + "\n" + "Horaires : " + current.getHoraireDebut() + " - " + current.getHoraireFin();
        holder.bind(bindingText);
    }

    static class ReservationDiff extends DiffUtil.ItemCallback<Reservation> {

        @Override
        public boolean areItemsTheSame(@NonNull Reservation oldItem, @NonNull Reservation newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Reservation oldItem, @NonNull Reservation newItem) {
            return oldItem.getReservationId() == newItem.getReservationId();
        }
    }
}
