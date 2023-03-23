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
public interface ReservationDao {

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy

    /**
     * Méthode d'insertion d'une réservation
     * @param reservation
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Reservation reservation);

    /**
     * Méthode permettant de supprimer l'ensemble des réservations
     */
    @Query("DELETE FROM reservation_table")
    void deleteAll();


    /**
     * Méthode qui retourne l'ensemble des réservation
     * @return
     */
    @Query("SELECT * FROM reservation_table ORDER BY reservation_id ASC")
    LiveData<List<Reservation>> getReservations();

    /**
     * Méthode qui retourne une réservation selon l'id passé en paramètre
     * @param reservationId
     * @return
     */
    @Query("SELECT * FROM reservation_table WHERE reservation_id = :reservationId")
    Reservation getReservationFromId(int reservationId);

    /**
     *  Méthode qui supprime une réservation selon l'id passé en paramètre
      * @param reservationId
     */
    @Query("DELETE FROM reservation_table WHERE reservation_id= :reservationId")
    void delete(int reservationId);
}
