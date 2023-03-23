package com.example.musclerent;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * Classe qui fait lien avec la source de données pour les données de type Salle.
 */
class SalleRepository {

    private SalleDao mSalleDao;
    private LiveData<List<Salle>> mAllSalles;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    SalleRepository(Application application) {
        MuscleRoomDatabase db = MuscleRoomDatabase.getDatabase(application);
        mSalleDao = db.salleDao();
        mAllSalles = mSalleDao.getSalles();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Salle>> getAllSalles() {
        return mAllSalles;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    /**
     * Méthode qui permet l'insertion d'une salle dans la base de donnée
     * @param salle la salle en question
     */
    void insert(Salle salle) {
        MuscleRoomDatabase.databaseWriteExecutor.execute(() -> {
            mSalleDao.insert(salle);
        });
    }
}
