package com.example.musclerent;

import android.util.Pair;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

/**
 * Classe qui va faire le pont entre les données et l'ui
 * Elle stocke les information des réservation et l'envoie à la vue
 */
public class ReservationListAdapter extends ListAdapter<Pair<Reservation, Pair<String,String>>, ReservationViewHolder> {

    private SalleViewModel mSalleViewModel;

    public ReservationListAdapter(@NonNull DiffUtil.ItemCallback<Pair<Reservation, Pair<String,String>>> diffCallback) {
        super(diffCallback);
    }

    @Override
    public ReservationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ReservationViewHolder.create(parent);
    }

    /**
     * Méthode qui est appelée pour afficher les données à la position spécifiée
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
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


    /**
     *  Classe qui va nous permettre certaines opérations sur les données
     */
    static class ReservationDiff extends DiffUtil.ItemCallback<Pair<Reservation, Pair<String,String>>> {

        /**
         * Appelée pour savoir si deux object representent le meme item
         * @param oldItem The item in the old list.
         * @param newItem The item in the new list.
         * @return
         */
        @Override
        public boolean areItemsTheSame(@NonNull Pair<Reservation, Pair<String,String>> oldItem, @NonNull Pair<Reservation, Pair<String,String>> newItem) {
            return oldItem == newItem;
        }

        /**
         * Méthode appelée pour savoir si deux items ont les mêmes données
         * @param oldItem The item in the old list.
         * @param newItem The item in the new list.
         * @return
         */
        @Override
        public boolean areContentsTheSame(@NonNull Pair<Reservation, Pair<String,String>> oldItem, @NonNull Pair<Reservation, Pair<String,String>> newItem) {
            return oldItem.first.getReservationId() == newItem.first.getReservationId();
        }
    }
}
