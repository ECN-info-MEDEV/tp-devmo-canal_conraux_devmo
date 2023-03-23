package com.example.musclerent;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * View model de reservation : permet la récupération asynchrone des données via le repository
 * pour assigner les valeur de l'attribut de type LiveData
 */
public class SalleViewModel extends AndroidViewModel {

    /**
     * Repository qui fait le lien avec les données de type salle
     */
    private SalleRepository mRepository;

    private final LiveData<List<Salle>> mAllSalles;

    /**
     * Méthode qui récupére les données des repository pour les enregistrer
     * @param application
     */
    public SalleViewModel (Application application) {
        super(application);
        mRepository = new SalleRepository(application);
        mAllSalles = mRepository.getAllSalles();
    }

    LiveData<List<Salle>> getAllSalles() { return mAllSalles; }

    /**
     * Méthode qui insère une salle dans la base de données
     * @param salle
     */
    public void insert(Salle salle) { mRepository.insert(salle); }
}
