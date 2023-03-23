package com.example.musclerent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

/**
 * Activité qui gère l'affichage des information à propos d'une réservation
 */
public class ReservationInformationActivity extends AppCompatActivity {

    //Modelviews to reach database
    private ReservationViewModel mReservationViewModel;
    private SalleViewModel mSalleViewModel;

    //Stockage infos
    private String nomSalle;
    private String adresseSalle;
    private String date;
    private String beginning;
    private String end;
    private String detailsSalle;


    //Textviews ou afficher les informations récupérées
    private TextView mGymName;
    private TextView mGymAdress;
    private TextView mGymDate;
    private TextView mGymBeginning;
    private TextView mGymEnd;
    private TextView mGymDetails;


    /**
     * Méthode onCreate qui va intialisé la plupart des entités de la classe (attibut, listener, ...)
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_information);

        //Recuperer infos dans l'intent
        Intent intent = getIntent();
        nomSalle = intent.getStringExtra(MainActivity.GYM_NAME);
        adresseSalle = intent.getStringExtra(MainActivity.GYM_ADRESS);
        detailsSalle = intent.getStringExtra(MainActivity.GYM_DETAILS);
        date = intent.getStringExtra(MainActivity.DATE);
        end = intent.getStringExtra(MainActivity.END);
        beginning = intent.getStringExtra(MainActivity.BEGINNING);



        //Lier les infos récupérées aux textviews que l'on affiche
        mGymName = findViewById(R.id.gym_name_info);
        mGymAdress = findViewById(R.id.gym_adress_info);
        mGymDate = findViewById(R.id.date_info);
        mGymBeginning = findViewById(R.id.res_beginning_info);
        mGymEnd = findViewById(R.id.res_end_info);
        mGymDetails = findViewById(R.id.gym_details_info);
        mGymName.setText(nomSalle);
        mGymAdress.setText(adresseSalle);
        mGymDate.setText(date);
        mGymBeginning.setText(beginning);
        mGymEnd.setText(end);
        mGymDetails.setText(detailsSalle);

    }
}