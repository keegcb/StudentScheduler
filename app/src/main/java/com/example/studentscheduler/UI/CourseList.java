package com.example.studentscheduler.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.studentscheduler.Database.Repository;
import com.example.studentscheduler.Entity.Course;
import com.example.studentscheduler.Entity.Term;
import com.example.studentscheduler.R;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class CourseList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView mRecyclerView = findViewById(R.id.recyclerview_course_list);
        Repository repo = new Repository(getApplication());
        List<Course> courses = repo.getAllCourses();
        final CourseAdapter adapter = new CourseAdapter(this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setCourseList(courses);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_refresh, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.refresh:
                finish();
                startActivity(getIntent());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void goToCourseAdd(View view) {
        Intent intent = new Intent(CourseList.this, CourseAdd.class);
        startActivity(intent);
    }
}