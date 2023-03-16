package com.example.musclerent;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Reservation.class, Salle.class}, version = 1, exportSchema = false)
public abstract class MuscleRoomDatabase extends RoomDatabase {

    public abstract ReservationDao reservationDao();

    private static volatile MuscleRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static MuscleRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MuscleRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    MuscleRoomDatabase.class, "muscle_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
