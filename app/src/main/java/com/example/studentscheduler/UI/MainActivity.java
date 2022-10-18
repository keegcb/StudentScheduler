package com.example.studentscheduler.UI;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.example.studentscheduler.Database.Repository;
import com.example.studentscheduler.Entity.Assessment;
import com.example.studentscheduler.Entity.Course;
import com.example.studentscheduler.Entity.Term;
import com.example.studentscheduler.R;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Repository repo = new Repository(getApplication());

/*
        Term term = new Term("Fall 2022", Date.from(Instant.now()), Date.from(Instant.now()));
        Term term2 = new Term("Winter 2022", Date.from(Instant.now()), Date.from(Instant.now()));
        Term term3 = new Term("Spring 2023", Date.from(Instant.now()), Date.from(Instant.now()));
        Term term4 = new Term("Summer 2023", Date.from(Instant.now()), Date.from(Instant.now()));
        Term term5 = new Term("Fall 2023", Date.from(Instant.now()), Date.from(Instant.now()));
        Course course = new Course("Physics", 1, Date.from(Instant.now()), Date.from(Instant.now()), 3, "Dr. Bromeo", "616-434-1129", "w.bromeo@wgu.edu");
        Course course2 = new Course("Math", 1, Date.from(Instant.now()), Date.from(Instant.now()), 1, "Dr. Bromeo", "616-434-1129", "w.bromeo@wgu.edu");
        Course course3 = new Course("Biology", 1, Date.from(Instant.now()), Date.from(Instant.now()), 2, "Dr. Bromeo", "616-434-1129", "w.bromeo@wgu.edu");
        Course course4 = new Course("History", 2, Date.from(Instant.now()), Date.from(Instant.now()), 4, "Dr. Bromeo", "616-434-1129", "w.bromeo@wgu.edu");
        Course course5 = new Course("Computer Science", 3, Date.from(Instant.now()), Date.from(Instant.now()), 4, "Dr. Bromeo", "616-434-1129", "w.bromeo@wgu.edu");
        Assessment assessment = new Assessment("Quiz", 1, "Objective", Date.from(Instant.now()), Date.from(Instant.now()), true);
        Assessment assessment2 = new Assessment("Midterm", 2, "Objective", Date.from(Instant.now()), Date.from(Instant.now()), true);
        Assessment assessment3 = new Assessment("Final", 2, "Objective", Date.from(Instant.now()), Date.from(Instant.now()), true);
        Assessment assessment4 = new Assessment("Project", 2, "Performance", Date.from(Instant.now()), Date.from(Instant.now()), true);
        Assessment assessment5 = new Assessment("Paper", 2, "Performance", Date.from(Instant.now()), Date.from(Instant.now()), true);
        repo.insertTerm(term);
        repo.insertTerm(term2);
        repo.insertTerm(term3);
        repo.insertTerm(term4);
        repo.insertTerm(term5);
        repo.insertCourse(course);
        repo.insertCourse(course2);
        repo.insertCourse(course3);
        repo.insertCourse(course4);
        repo.insertCourse(course5);
        repo.insertAssessment(assessment);
        repo.insertAssessment(assessment2);
        repo.insertAssessment(assessment3);
        repo.insertAssessment(assessment4);
        repo.insertAssessment(assessment5);
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