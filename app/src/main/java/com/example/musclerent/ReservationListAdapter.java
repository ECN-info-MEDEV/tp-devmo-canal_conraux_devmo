package com.example.musclerent;

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
        holder.bind(current.getDate());
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
