package com.example.musclerent;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

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

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(Reservation reservation) {
        MuscleRoomDatabase.databaseWriteExecutor.execute(() -> {
            mReservationDao.insert(reservation);
        });
    }
}
