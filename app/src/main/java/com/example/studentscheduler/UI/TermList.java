package com.example.studentscheduler.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.studentscheduler.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TermList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_list);

        FloatingActionButton newTerm = findViewById(R.id.btn_new_term);
        newTerm.setOnClickListener(v -> {
            Intent intent= new Intent(TermList.this, TermUpdate.class);
            startActivityForResult(intent, /* name of SQL code in */ );
        });
    }
}