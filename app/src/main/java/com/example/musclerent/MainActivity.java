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
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ReservationViewModel mReservationViewModel;

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //View of reservations of the database
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final ReservationListAdapter adapter = new ReservationListAdapter(new ReservationListAdapter.ReservationDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Add data to dB when response
        mReservationViewModel = new ViewModelProvider(this).get(ReservationViewModel.class);
        mReservationViewModel.getAllReservations().observe(this, reservations -> {
            // Update the cached copy of the words in the adapter.
            adapter.submitList(reservations);
        });

        //Send to new reservation when clicking on floating button
        FloatingActionButton floating = findViewById(R.id.fab);
        floating.setOnClickListener( view -> {
            Intent intent = new Intent(MainActivity.this, NewReservationActivity.class);
            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
        });
    }



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
                        List<Reservation> reservations = (List<Reservation>)mReservationViewModel.getAllReservations();
                        for ( Reservation reservation : reservations) {
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
}