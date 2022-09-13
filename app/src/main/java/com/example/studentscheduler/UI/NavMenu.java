package com.example.studentscheduler.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.studentscheduler.R;

public class NavMenu extends AppCompatActivity {
    private Button termList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_menu);

        termList = findViewById(R.id.btn_term_select);
        termList.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), TermList.class);
            v.getContext().startActivity(intent);
        });
    }
}