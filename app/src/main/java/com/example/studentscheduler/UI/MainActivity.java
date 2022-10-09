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

    //Creates dummy data to add to the local DB on start
/*
        for (int i=1; i<6; i++){
            String tname = "";
            String cname = "";
            String aname = "";
            int status = 1;


            switch(i){
                case 1:
                    tname = "Fall Term";
                    cname = "Biology";
                    aname = "Midterm";
                    break;
                case 2:
                    tname = "Winter Term";
                    cname = "Math";
                    aname = "Report";
                    status = 2;
                    break;
                case 3:
                    tname = "Spring Term";
                    cname = "Computers";
                    aname = "Project";
                    status = 2;
                    break;
                case 4:
                    tname ="Summer Term";
                    cname = "Statistics";
                    aname = "Final";
                    break;
                case 5:
                    tname = "Study Abroad";
                    cname = "Hisotry";
                    aname = "Peer Review";
                    break;
            }

            Term term = new Term(tname, Date.from(Instant.now().plus(i-1, ChronoUnit.MONTHS)), Date.from(Instant.now().plus(i, ChronoUnit.MONTHS)));
            Course course = new Course(cname, Date.from(Instant.now().plus(i-1, ChronoUnit.MONTHS)), Date.from(Instant.now().plus(i,ChronoUnit.MONTHS)), status, "Dr. Bromeo", "616-434-1129", "w.bromeo@wgu.edu");
            Assessment assessment = new Assessment(aname, i, Date.from(Instant.now().plus(i-1, ChronoUnit.MONTHS)), Date.from(Instant.now().plus(i, ChronoUnit.MONTHS)), true);
            repo.insertTerm(term);
            repo.insertCourse(course);
            repo.insertAssessment(assessment);
        }
 */

/*
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