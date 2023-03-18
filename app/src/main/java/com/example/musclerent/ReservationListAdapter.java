package com.example.musclerent;

import android.app.Application;
import android.util.Pair;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class ReservationListAdapter extends ListAdapter<Pair<Reservation, Pair<String,String>>, ReservationViewHolder> {

    private SalleViewModel mSalleViewModel;

    public ReservationListAdapter(@NonNull DiffUtil.ItemCallback<Pair<Reservation, Pair<String,String>>> diffCallback) {
        super(diffCallback);
    }

    @Override
    public ReservationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ReservationViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(ReservationViewHolder holder, int position) {

        Reservation current = getItem(position).first;

        //Retrieving salle info
        String nomSalle = getItem(position).second.first;
        String adresseSalle = getItem(position).second.second;


        String bindingText = "Salle : " + nomSalle +  "\n" + "Adresse : " + adresseSalle +  "\n" + "Date : " + current.getDate() + "\n" + "Horaires : " + current.getHoraireDebut() + " - " + current.getHoraireFin();

        //Operations on holder
        holder.bind(bindingText);
        holder.deleteGiveTagId(String.valueOf(current.getReservationId()));
        holder.infoGiveTagId(String.valueOf(current.getReservationId()));
    }

    static class ReservationDiff extends DiffUtil.ItemCallback<Pair<Reservation, Pair<String,String>>> {

        @Override
        public boolean areItemsTheSame(@NonNull Pair<Reservation, Pair<String,String>> oldItem, @NonNull Pair<Reservation, Pair<String,String>> newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Pair<Reservation, Pair<String,String>> oldItem, @NonNull Pair<Reservation, Pair<String,String>> newItem) {
            return oldItem.first.getReservationId() == newItem.first.getReservationId();
        }
    }
}
