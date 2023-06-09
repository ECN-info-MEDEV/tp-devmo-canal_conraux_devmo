package com.example.musclerent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe contient le code pour l'activité principale de l'application qui affiche les reservation
 * en cours, permet de voir leurs détails et de les supprimer et d'en ajouter des nouvelles.
 */
public class MainActivity extends AppCompatActivity {

    private ReservationViewModel mReservationViewModel;
    private SalleViewModel mSalleViewModel;

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    public static final String EXTRA_MESSAGE =
            "com.example.musclerent.extra.MESSAGE";
    public static final String GYM_NAME =
            "com.example.musclerent.extra.GYMNAME";
    public static final String GYM_ADRESS =
            "com.example.musclerent.extra.GYMADRESS";
    public static final String DATE =
            "com.example.musclerent.extra.DATE";
    public static final String BEGINNING =
            "com.example.musclerent.extra.BEGINNING";
    public static final String END =
            "com.example.musclerent.extra.END";
    public static final String GYM_DETAILS =
            "com.example.musclerent.extra.GYMDETAILS";

    /**
     * Méthode onCreate qui va intialisé la plupart des entités de la classe (attibut, listener, ...)
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //View of reservations of the database
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ReservationListAdapter adapter = new ReservationListAdapter(new ReservationListAdapter.ReservationDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mSalleViewModel = new ViewModelProvider(this).get(SalleViewModel.class);
        mSalleViewModel.getAllSalles().observe(this, salles -> {
        });

        LiveData<List<Salle>> salles = mSalleViewModel.getAllSalles();
        //Add data to dB when response
        mReservationViewModel = new ViewModelProvider(this).get(ReservationViewModel.class);
        mReservationViewModel.getAllReservations().observe(this, reservations -> {
            // Update the cached copy of the words in the adapter.
            List<Pair<Reservation, Pair<String,String>>> reservationsmore = new ArrayList<>();
            for (Salle salle : salles.getValue()) {
                for (Reservation reservation : reservations) {
                    if (salle.getSalleId() == reservation.getSalleId()) {
                        reservationsmore.add(Pair.create(reservation, Pair.create(salle.getNom(),salle.getAdresse())));
                    }
                }
            }
            adapter.submitList(reservationsmore);
        });


        //Send to new reservation when clicking on floating button
        FloatingActionButton floating = findViewById(R.id.fab);
        floating.setOnClickListener( view -> {
            Intent intent = new Intent(MainActivity.this, NewReservationActivity.class);
            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
        });
    }


    /**
     * Cette méthode s'exécute lors du retour de l'activité lié a la réservation d'une séance
     *
     * @param requestCode permet de tester de quelle activité provient le résultat
     * @param resultCode informe sur le résultat
     * @param data  contient les données retournées par l'autre activité
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Reservation reservation = new Reservation(data.getStringExtra(NewReservationActivity.EXTRA_REPLY_DATE),data.getStringExtra(NewReservationActivity.EXTRA_REPLY_BEGINNING), data.getStringExtra(NewReservationActivity.EXTRA_REPLY_END), Integer.valueOf(data.getStringExtra(NewReservationActivity.EXTRA_REPLY_SALLE)));
            mReservationViewModel.insert(reservation);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Méthode qui va afficher une fenêtre demandant une confirmation lors de la suppression d'une reservation
     * @param view bloc de base permettant la création d'un layout
     */
    public void showDeleteAlert(View view) {
        int reservationId = Integer.parseInt((String)view.getTag());
        //Create the alert when deleting something
        AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
        builder1.setMessage("Are you sure you want to delete your booking ?");
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        LiveData<List<Reservation>> reservations = mReservationViewModel.getAllReservations();
                        for ( Reservation reservation : reservations.getValue()) {
                            if (reservation.getReservationId() == reservationId) {
                                mReservationViewModel.delete(reservation);
                            }
                        }
                    }
                });
        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = builder1.show();
    }

    /**
     * Méthode qui va lancer l'activité information de réservation en lui passant les paramètres de la réservation souhaitée par l'utilsateur
     * @param view bloc de base permettant la création d'un layout
     */
    public void goInformationIntent(View view) {
        //Takes salle and reservation info
        int reservationId = Integer.valueOf((String)view.getTag());
        int idSalle = -1;
        //Stockage infos
        String nomSalle = "";
        String adresseSalle = "";
        String date = "";
        String beginning = "";
        String end = "";
        String detailsSalle = "";
        //Search for info and stock
        LiveData<List<Reservation>> reservations = mReservationViewModel.getAllReservations();
        for ( Reservation reservation : reservations.getValue()) {
            if (reservation.getReservationId() == reservationId) {
                date = reservation.getDate();
                beginning = reservation.getHoraireDebut();
                end = reservation.getHoraireFin();
                idSalle = reservation.getSalleId();
            }
        }
        if (idSalle != -1) {
            mSalleViewModel = new ViewModelProvider(this).get(SalleViewModel.class);
            LiveData<List<Salle>> salles = mSalleViewModel.getAllSalles();
            for (Salle salle : salles.getValue()) {
                if (salle.getSalleId() == idSalle) {
                    nomSalle = salle.getNom();
                    adresseSalle = salle.getAdresse();
                    detailsSalle = salle.getDetails();
                }
            }
        }

        //Put information in intent as extras
        Intent intent = new Intent(MainActivity.this, ReservationInformationActivity.class);
        intent.putExtra(GYM_NAME, nomSalle);
        intent.putExtra(GYM_ADRESS, adresseSalle);
        intent.putExtra(GYM_DETAILS, detailsSalle);
        intent.putExtra(DATE, date);
        intent.putExtra(BEGINNING, beginning);
        intent.putExtra(END, end);

        //Start activity
        startActivity(intent);
    }
}