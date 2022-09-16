package com.example.studentscheduler.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.studentscheduler.Database.Repository;
import com.example.studentscheduler.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Repository rep = new Repository(getApplication());

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