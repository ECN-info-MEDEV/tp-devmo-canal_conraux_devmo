package com.example.musclerent;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ReservationDao {

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Reservation reservation);

    @Query("DELETE FROM reservation_table")
    void deleteAll();

    @Query("SELECT * FROM reservation_table ORDER BY reservation_id ASC")
    LiveData<List<Reservation>> getReservations();
}
