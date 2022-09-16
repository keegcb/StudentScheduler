package com.example.studentscheduler.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.studentscheduler.R;

public class TermUpdate extends AppCompatActivity {

    public static final String EDIT_TERM_TITLE = "com.example.studentscheduler.activities.TERM_TITLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_update);
    }
}