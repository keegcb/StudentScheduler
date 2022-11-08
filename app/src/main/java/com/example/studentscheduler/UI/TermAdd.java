package com.example.studentscheduler.UI;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.studentscheduler.Database.DateConverter;
import com.example.studentscheduler.Database.Repository;
import com.example.studentscheduler.Entity.Course;
import com.example.studentscheduler.Entity.Term;
import com.example.studentscheduler.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TermAdd extends AppCompatActivity {

    public static final String EDIT_TERM_TITLE = "com.example.studentscheduler.activities.TERM_TITLE";
    TextView termID;
    EditText termName;
    Button startDate;
    Button endDate;
    Button save;

    DatePickerDialog.OnDateSetListener sDate;
    final Calendar sCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener eDate;
    final Calendar eCalendar = Calendar.getInstance();

    String id;
    String title;
    Long sD;
    Long eD;
    private Term maxTerm;
    private boolean hasValues = false;
    Repository repo = new Repository(getApplication());
    String mFormat = "MM/dd/yy";
    SimpleDateFormat sdf = new SimpleDateFormat(mFormat, Locale.US);

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_details);

        termID = findViewById(R.id.editText_termID);
        termName = findViewById(R.id.editText_termName);
        startDate = findViewById(R.id.btn_startDate);
        endDate = findViewById(R.id.btn_endDate);
        save = findViewById(R.id.btn_saveTerm);

        int tId = repo.getMaxTermId();
        id = Integer.toString(tId+1);

        sD = DateConverter.toTimestamp(Date.from(Instant.now()));
        eD = DateConverter.toTimestamp(Date.from(Instant.now()));

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
                new DatePickerDialog(TermAdd.this, sDate, sCalendar.get(Calendar.YEAR),
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
                new DatePickerDialog(TermAdd.this, eDate, eCalendar.get(Calendar.YEAR),
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


    public void saveTerm(View view){
        Date nStartDate = sCalendar.getTime();
        Date nEndDate = eCalendar.getTime();

        if (!termName.getText().toString().equals("")) {
            Term nTerm = new Term(title, nStartDate, nEndDate);
            repo.insertTerm(nTerm);
            this.finish();
        }
    }
}