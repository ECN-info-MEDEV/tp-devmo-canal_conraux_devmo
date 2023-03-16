package com.example.musclerent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class NewReservationActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY_DATE = "com.example.musclerent.REPLY_DATE";
    public static final String EXTRA_REPLY_BEGINNING = "com.example.musclerent.REPLY_BEGINNING";
    public static final String EXTRA_REPLY_END = "com.example.musclerent.REPLY_END";
    public static final String EXTRA_REPLY_SALLE = "com.example.musclerent.REPLY_SALLE";

    private EditText mEditDateView;
    private EditText mEditBeginningView;
    private EditText mEditEndView;
    private EditText mEditSalleView;

    private SalleViewModel mSalleViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reservation);
        mEditDateView = findViewById(R.id.date_edit);
        mEditBeginningView = findViewById(R.id.beginning_edit);
        mEditEndView = findViewById(R.id.end_edit);
        mEditSalleView = findViewById(R.id.salle_edit);


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

        final Button button = findViewById(R.id.submit_button);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mEditDateView.getText()) || TextUtils.isEmpty(mEditBeginningView.getText()) || TextUtils.isEmpty(mEditEndView.getText()) ||TextUtils.isEmpty(mEditSalleView.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String date = mEditDateView.getText().toString();
                String beginning = mEditBeginningView.getText().toString();
                String end = mEditEndView.getText().toString();
                String salle = mEditSalleView.getText().toString();
                replyIntent.putExtra(EXTRA_REPLY_DATE, date);
                replyIntent.putExtra(EXTRA_REPLY_BEGINNING, beginning);
                replyIntent.putExtra(EXTRA_REPLY_END, end);
                replyIntent.putExtra(EXTRA_REPLY_SALLE, salle);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}