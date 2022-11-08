package com.example.studentscheduler.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.studentscheduler.Database.DateConverter;
import com.example.studentscheduler.Database.Repository;
import com.example.studentscheduler.Entity.Assessment;
import com.example.studentscheduler.Entity.Course;
import com.example.studentscheduler.Entity.Term;
import com.example.studentscheduler.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TermDetails extends AppCompatActivity {
    TextView termID;
    EditText termName;
    Button startDate;
    Button endDate;

    DatePickerDialog.OnDateSetListener sDate;
    final Calendar sCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener eDate;
    final Calendar eCalendar = Calendar.getInstance();

    String id;
    String title;
    long sD;
    long eD;
    Repository repo = new Repository(getApplication());
    Context mContex;
    private boolean noCourse = false;
    String mFormat = "MM/dd/yy";
    SimpleDateFormat sdf = new SimpleDateFormat(mFormat, Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_details);

        termID = findViewById(R.id.editText_termID);
        termName = findViewById(R.id.editText_termName);
        startDate = findViewById(R.id.btn_startDate);
        endDate = findViewById(R.id.btn_endDate);

        id = getIntent().getStringExtra("id");
        title = getIntent().getStringExtra("title");
        sD = getIntent().getLongExtra("start", DateConverter.toTimestamp(new Date()));
        eD = getIntent().getLongExtra("end", DateConverter.toTimestamp(new Date()));

        termName.setText(title);
        String currentStartDate = sdf.format(sD);
        String currentEndDate = sdf.format(eD);
        startDate.setText(currentStartDate);
        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date;
                String info = startDate.getText().toString();
                try{
                    sCalendar.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(TermDetails.this, sDate, sCalendar.get(Calendar.YEAR),
                        sCalendar.get(Calendar.MONTH), sCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        sDate = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day){
                sCalendar.set(Calendar.YEAR, year);
                sCalendar.set(Calendar.MONTH, month);
                sCalendar.set(Calendar.DAY_OF_MONTH, day);
                updateStartDate();
            }
        };

        endDate.setText(currentEndDate);
        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date;
                String info = endDate.getText().toString();
                try{
                    eCalendar.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(TermDetails.this, eDate, eCalendar.get(Calendar.YEAR),
                        eCalendar.get(Calendar.MONTH), eCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        eDate = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day){
                eCalendar.set(Calendar.YEAR, year);
                eCalendar.set(Calendar.MONTH, month);
                eCalendar.set(Calendar.DAY_OF_MONTH, day);
                updateEndDate();
            }
        };

        termID.setText(id);
        termName.setText(title);
    }

    public void updateStartDate() {
        startDate.setText(sdf.format(sCalendar.getTime()));
    }
    public void updateEndDate() {
        endDate.setText(sdf.format(eCalendar.getTime()));
    }

    public void deleteTerm(View view){
        if (repo.termCourse(Integer.parseInt(id)) == null){
            noCourse = true;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if(noCourse){
            builder.setCancelable(true);
            builder.setMessage("Are you sure you want to delete this term?");
            builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    repo.deleteTerm(repo.getTermInfo(Integer.parseInt(id)));
                }
            });
            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            List<Course> termCourses = repo.termCourse(Integer.parseInt(id));
            builder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //OK button confirms message but does not perform action
                }
            });
            builder.setMessage("Course cannot be deleted while existing courses are associated." + termCourses.toString());
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    public void saveTerm(View view){
        Date nStartDate = sCalendar.getTime();
        Date nEndDate = eCalendar.getTime();

        //TODO: Fix update so it updates the specific term in the DB based on ID
        if (!termName.getText().toString().equals("")) {
            Term nTerm = new Term(title, nStartDate, nEndDate);
            nTerm.setTermId(Integer.parseInt(id));
            repo.updateTerm(nTerm);
            this.finish();
        }
    }
}