package com.example.musclerent;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SalleDao {

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Salle salle);

    @Query("DELETE FROM salle_table")
    void deleteAll();

    @Query("SELECT * FROM salle_table ORDER BY salle_id ASC")
    LiveData<List<Salle>> getSalles();
}
