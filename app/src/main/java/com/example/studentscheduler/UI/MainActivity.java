package com.example.studentscheduler.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.studentscheduler.Database.Repository;
import com.example.studentscheduler.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Repository rep = new Repository(getApplication());

        Button termButton = findViewById(R.id.btn_termList);
        Button courseButton = findViewById(R.id.btn_courseList);
        Button assessmentButton = findViewById(R.id.btn_assessmentList);

        termButton.setOnClickListener(this::BeginTermActivity);
        courseButton.setOnClickListener(this::BeginCourseActivity);
        assessmentButton.setOnClickListener(this::BeginAssessmentActivity);
    }

    public void BeginTermActivity(View view){
        Intent intent = new Intent(MainActivity.this, TermList.class);
        startActivity(intent);

    }

    public void BeginCourseActivity(View view){
        Intent intent = new Intent(MainActivity.this, CourseList.class);
        startActivity(intent);

    }

    public void BeginAssessmentActivity(View view){
        Intent intent = new Intent(MainActivity.this, AssessmentList.class);
        startActivity(intent);

    }

}