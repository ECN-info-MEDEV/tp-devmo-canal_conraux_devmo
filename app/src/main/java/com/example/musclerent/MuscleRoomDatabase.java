package com.example.musclerent;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Classe qui implémente la base de donnée de lapplication
 */
@Database(entities = {Reservation.class, Salle.class}, version = 1, exportSchema = false)
public abstract class MuscleRoomDatabase extends RoomDatabase {

    public abstract ReservationDao reservationDao();
    public abstract SalleDao salleDao();


    private static volatile MuscleRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    /**
     * Méthode qui retourne une instance de MuscleRoomDatabase
     * @param context le contexte de l'application
     * @return
     */
    static MuscleRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MuscleRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    MuscleRoomDatabase.class, "muscle_database").addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Callback qui va initialisé la liste de reservation dans la base de données
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // Salle populating
                SalleDao dao = INSTANCE.salleDao();
                dao.deleteAll();

                Salle salle = new Salle("Salle Delacour", "44 rue des Jardins, 44000 Nantes", "Cette salle est très cool.");
                dao.insert(salle);
                salle = new Salle("Salle Argos", "44 rue des prés, 44300 Nantes", "Cette salle est super très cool.");;
                dao.insert(salle);
                salle = new Salle("Salle Thesee", "121 rue du Minotaure, 12322 Crete", "Cette salle est mythologiquement cool.");;
                dao.insert(salle);

                //Reservation populating
                ReservationDao rdao = INSTANCE.reservationDao();
                rdao.deleteAll();

                Reservation reservation = new Reservation("25/03/2023", "13h", "15h", 1);
                rdao.insert(reservation);

            });
        }
    };
}
