package com.example.musclerent;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

/**
 * Interface qui définis des méthodes avec leur requête associée
 */
@Dao
public interface SalleDao {

    /**
     * Méthode d'insertion d'une salle dans la bdd
     * @param salle
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Salle salle);


    /**
     * Méthode permettant de supprimer l'ensemble des réservations
     */
    @Query("DELETE FROM salle_table")
    void deleteAll();

    /**
     * Méthode qui retourne l'ensemble des réservation
     * @return
     */
    @Query("SELECT * FROM salle_table ORDER BY salle_id ASC")
    LiveData<List<Salle>> getSalles();
}
