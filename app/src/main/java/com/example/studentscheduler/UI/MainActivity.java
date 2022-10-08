package com.example.studentscheduler.UI;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.studentscheduler.Database.Repository;
import com.example.studentscheduler.Entity.Assessment;
import com.example.studentscheduler.Entity.Course;
import com.example.studentscheduler.Entity.Term;
import com.example.studentscheduler.R;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.Month;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Repository repo = new Repository(getApplication());

    //Creates dummy data to add to the local DB on start
        /*
        for (long i=-1; i<5; i++){
            Term term = new Term("Fall Term", Date.from(Instant.now().), Date.from(Instant.now()));
        }
        Term term = new Term("Fall Term", Date.from(Instant.now()), Date.from(Instant.now()));
        Course course = new Course("Math", Date.from(Instant.now()), Date.from(Instant.now()), 1, "Dr. Bromeo", "616-434-1129", "w.bromeo@wgu.edu");
        Assessment assessment = new Assessment("Final", 1, Date.from(Instant.now()), Date.from(Instant.now()), true);
        repo.insertTerm(term);
        repo.insertCourse(course);
        repo.insertAssessment(assessment);
         */


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