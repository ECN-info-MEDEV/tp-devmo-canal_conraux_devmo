package com.example.musclerent;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class SalleViewModel extends AndroidViewModel {

    private SalleRepository mRepository;

    private final LiveData<List<Salle>> mAllSalles;

    public SalleViewModel (Application application) {
        super(application);
        mRepository = new SalleRepository(application);
        mAllSalles = mRepository.getAllSalles();
    }

    LiveData<List<Salle>> getAllSalles() { return mAllSalles; }

    public void insert(Salle salle) { mRepository.insert(salle); }
}
