package com.example.musclerent;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * View model de reservation : permet la récupération asynchrone des données via le repository
 * pour assigner les valeur de l'attribut de type LiveData
 */
public class ReservationViewModel extends AndroidViewModel {

    /**
     * Repository qui fait le lien avec les données de type reservation
     */
    private ReservationRepository mRepository;


    private final LiveData<List<Reservation>> mAllReservations;

    private SalleRepository mSalleRepository;
    private final LiveData<List<Salle>> mAllSalles;

    /**
     * Méthode qui récupére les données des repository pour les enregistrer
     * @param application
     */
    public ReservationViewModel(Application application) {
        super(application);
        mRepository = new ReservationRepository(application);
        mAllReservations = mRepository.getAllReservations();
        mSalleRepository = new SalleRepository(application);
        mAllSalles = mSalleRepository.getAllSalles();
    }

    LiveData<List<Reservation>> getAllReservations() { return mAllReservations; }

    /**
     * Méthode qui retourne une reservation en fonction de l'id passer en argument
     * @param id l'id de la réservation
     * @return
     */
    Reservation getReservationFromId(int id) {
        return mRepository.getReservationFromId(id);
    }

    /**
     * Méthode qui insère une réservation dans la base de données
     * @param reservation
     */
    public void insert(Reservation reservation) { mRepository.insert(reservation); }

    /**
     * Méthode qui supprime une réservation de la base de données
     * @param reservation
     */
    public void delete(Reservation reservation) { mRepository.delete(reservation);}
}
