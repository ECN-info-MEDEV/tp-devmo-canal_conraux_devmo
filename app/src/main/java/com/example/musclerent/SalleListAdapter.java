package com.example.musclerent;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

/**
 * Classe qui va faire le pont entre les données et l'ui
 * Elle stocke les information des salle et l'envoie à la vue
 */
public class SalleListAdapter extends ListAdapter<Salle, SalleViewHolder> {

    public SalleListAdapter(@NonNull DiffUtil.ItemCallback<Salle> diffCallback) {
        super(diffCallback);
    }

    @Override
    public SalleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return SalleViewHolder.create(parent);
    }

    /**
     * Méthode qui est appelée pour afficher les données à la position spécifiée
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(SalleViewHolder holder, int position) {
        Salle current = getItem(position);
        String bindingText = "N° salle : " + current.getSalleId() + "\nNom : " + current.getNom() ;
        holder.bind(bindingText);
    }

    /**
     *  Classe qui va nous permettre certaines opérations sur les données
     */
    static class SalleDiff extends DiffUtil.ItemCallback<Salle> {

        /**
         * Appelée pour savoir si deux object representent le meme item
         * @param oldItem The item in the old list.
         * @param newItem The item in the new list.
         * @return
         */
        @Override
        public boolean areItemsTheSame(@NonNull Salle oldItem, @NonNull Salle newItem) {
            return oldItem == newItem;
        }

        /**
         * Méthode appelée pour savoir si deux items ont les mêmes données
         * @param oldItem The item in the old list.
         * @param newItem The item in the new list.
         * @return
         */
        @Override
        public boolean areContentsTheSame(@NonNull Salle oldItem, @NonNull Salle newItem) {
            return oldItem.getSalleId() == newItem.getSalleId();
        }
    }
}
