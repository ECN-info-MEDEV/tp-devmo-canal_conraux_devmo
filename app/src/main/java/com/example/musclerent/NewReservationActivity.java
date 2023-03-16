package com.example.musclerent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class NewReservationActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

    public static final String EXTRA_REPLY_DATE = "com.example.musclerent.REPLY_DATE";
    public static final String EXTRA_REPLY_BEGINNING = "com.example.musclerent.REPLY_BEGINNING";
    public static final String EXTRA_REPLY_END = "com.example.musclerent.REPLY_END";
    public static final String EXTRA_REPLY_SALLE = "com.example.musclerent.REPLY_SALLE";

    private EditText mEditDateView;
    private EditText mEditBeginningView;
    private EditText mEditEndView;

    private String salleNumber;

    private SalleViewModel mSalleViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reservation);
        mEditDateView = findViewById(R.id.date_edit);
        mEditBeginningView = findViewById(R.id.beginning_edit);
        mEditEndView = findViewById(R.id.end_edit);
        salleNumber = "1";

        //View of salles of the database
        RecyclerView recyclerViewSalle = findViewById(R.id.recyclerviewsalle);
        final SalleListAdapter adapter = new SalleListAdapter(new SalleListAdapter.SalleDiff());
        recyclerViewSalle.setAdapter(adapter);
        recyclerViewSalle.setLayoutManager(new LinearLayoutManager(this));

        //Add data to dB when response
        mSalleViewModel = new ViewModelProvider(this).get(SalleViewModel.class);
        mSalleViewModel.getAllSalles().observe(this, salles -> {
            // Update the cached copy of the words in the adapter.
            adapter.submitList(salles);
        });

        // Create the spinner.
        Spinner spinner = findViewById(R.id.salle_spinner);
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }

        //Create an adapted list for the spinner from ressources
        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this,
                R.array.salle_numbers, android.R.layout.simple_spinner_item);
        adapterSpinner.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        //Apply the adapted list to the spinner
        if (spinner != null) {
            spinner.setAdapter(adapterSpinner);
        }

        final Button button = findViewById(R.id.submit_button);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mEditDateView.getText()) || TextUtils.isEmpty(mEditBeginningView.getText()) || TextUtils.isEmpty(mEditEndView.getText()) ||(salleNumber == null)) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String date = mEditDateView.getText().toString();
                String beginning = mEditBeginningView.getText().toString();
                String end = mEditEndView.getText().toString();
                String salle = salleNumber;
                replyIntent.putExtra(EXTRA_REPLY_DATE, date);
                replyIntent.putExtra(EXTRA_REPLY_BEGINNING, beginning);
                replyIntent.putExtra(EXTRA_REPLY_END, end);
                replyIntent.putExtra(EXTRA_REPLY_SALLE, salle);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int
            i, long l) {
        salleNumber = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}