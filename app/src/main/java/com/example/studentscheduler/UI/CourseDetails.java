package com.example.studentscheduler.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.studentscheduler.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CourseDetails extends AppCompatActivity {

    TextView courseId;
    EditText courseTitle;
    Button startDate;
    Button endDate;
    Spinner statusSpinner;
    EditText instructor;
    EditText email;
    EditText phone;
    Button termSelect;
    Button courseDelete;
    Button courseSave;

    DatePickerDialog.OnDateSetListener sDate;
    final Calendar sCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener eDate;
    final Calendar eCalendar = Calendar.getInstance();

    String id;
    String cTitle;
    long sD;
    long eD;
    String cStatus;
    String instructorName;
    String instructorEmail;
    String instructorPhone;


    //TODO: Create layout UI and add logic

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detials);

        courseId = findViewById(R.id.textView_courseId);
        courseTitle = findViewById(R.id.editTxt_courseTitle);
        statusSpinner = findViewById(R.id.spn_courseStatus);
        ArrayAdapter<CharSequence> statusAdapter = ArrayAdapter.createFromResource(this, R.array.course_status, android.R.layout.simple_spinner_item);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        statusSpinner.setAdapter(statusAdapter);
        statusSpinner.setSelection(0);
        statusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //TODO: Add logic to show selected item in spinner
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        instructor = findViewById(R.id.editText_name);
        email = findViewById(R.id.editText_email);
        phone = findViewById(R.id.editText_phone);
        termSelect = findViewById(R.id.btn_courseList);
        startDate = findViewById(R.id.btn_courseStart);
        endDate = findViewById(R.id.btn_courseEnd);
        courseDelete = findViewById(R.id.btn_deleteCourse);
        courseSave = findViewById(R.id.btn_saveCourse);

        id = getIntent().getStringExtra("id");
        cTitle = getIntent().getStringExtra("title");
        cStatus = getIntent().getStringExtra("status");
        instructorName = getIntent().getStringExtra("name");
        instructorEmail = getIntent().getStringExtra("email");
        instructorPhone = getIntent().getStringExtra("phone");

        courseId.setText(id);
        courseTitle.setText(cTitle);
    //TODO: Set spinner value to reflect status of course

        String mFormat = "MM/dd/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(mFormat, Locale.US);
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
                new DatePickerDialog(CourseDetails.this, sDate, sCalendar.get(Calendar.DAY_OF_MONTH),
                        sCalendar.get(Calendar.MONTH), sCalendar.get(Calendar.YEAR)).show();
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
                new DatePickerDialog(CourseDetails.this, eDate, eCalendar.get(Calendar.DAY_OF_MONTH),
                        eCalendar.get(Calendar.MONTH), eCalendar.get(Calendar.YEAR)).show();
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
    }

    public void updateStartDate() {
        String mFormat = "MM/dd/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(mFormat, Locale.US);

        startDate.setText(sdf.format(sCalendar.getTime()));
    }
    public void updateEndDate() {
        String mFormat = "MM/dd/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(mFormat, Locale.US);

        endDate.setText(sdf.format(eCalendar.getTime()));
    }
}