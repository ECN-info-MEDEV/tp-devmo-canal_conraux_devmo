package com.example.musclerent;

import androidx.appcompat.app.AppCompatActivity;

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

    private EditText mEditDateView;
    private EditText mEditBeginningView;
    private EditText mEditEndView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reservation);
        mEditDateView = findViewById(R.id.date_edit);
        mEditBeginningView = findViewById(R.id.beginning_edit);
        mEditEndView = findViewById(R.id.end_edit);

        final Button button = findViewById(R.id.submit_button);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(mEditDateView.getText()) || TextUtils.isEmpty(mEditBeginningView.getText()) || TextUtils.isEmpty(mEditEndView.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String date = mEditDateView.getText().toString();
                String beginning = mEditBeginningView.getText().toString();
                String end = mEditEndView.getText().toString();
                replyIntent.putExtra(EXTRA_REPLY_DATE, date);
                replyIntent.putExtra(EXTRA_REPLY_BEGINNING, beginning);
                replyIntent.putExtra(EXTRA_REPLY_END, end);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}