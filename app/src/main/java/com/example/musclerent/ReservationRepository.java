package com.example.musclerent;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * Classe qui fait lien avec la source de données pour les données de type Reservation.
 */
class ReservationRepository {

    private ReservationDao mReservationDao;
    private LiveData<List<Reservation>> mAllReservations;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    ReservationRepository(Application application) {
        MuscleRoomDatabase db = MuscleRoomDatabase.getDatabase(application);
        mReservationDao = db.reservationDao();
        mAllReservations = mReservationDao.getReservations();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Reservation>> getAllReservations() {
        return mAllReservations;
    }

    Reservation getReservationFromId(int id) {
        return mReservationDao.getReservationFromId(id);
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.

    /**
     * Méthode qui permet l'insertion d'une reservation dans la base de donnée
     * @param reservation la réservation en question
     */
    void insert(Reservation reservation) {
        MuscleRoomDatabase.databaseWriteExecutor.execute(() -> {
            mReservationDao.insert(reservation);
        });
    }


    /**
     * Méthode qui permet la suppression d'une reservation dans la base de donnée
     * @param reservation la réservation en question
     */
    void delete(Reservation reservation) {
        MuscleRoomDatabase.databaseWriteExecutor.execute(() -> {
            mReservationDao.delete(reservation.getReservationId());
        });
    }
}
