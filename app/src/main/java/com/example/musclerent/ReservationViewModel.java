package com.example.musclerent;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ReservationViewModel extends AndroidViewModel {

    private ReservationRepository mRepository;

    private final LiveData<List<Reservation>> mAllReservations;

    private SalleRepository mSalleRepository;
    private final LiveData<List<Salle>> mAllSalles;

    public ReservationViewModel(Application application) {
        super(application);
        mRepository = new ReservationRepository(application);
        mAllReservations = mRepository.getAllReservations();
        mSalleRepository = new SalleRepository(application);
        mAllSalles = mSalleRepository.getAllSalles();
    }

    LiveData<List<Reservation>> getAllReservations() { return mAllReservations; }

    public void insert(Reservation reservation) { mRepository.insert(reservation); }
}
