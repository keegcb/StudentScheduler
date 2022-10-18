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

import com.example.studentscheduler.Database.Repository;
import com.example.studentscheduler.Entity.Course;
import com.example.studentscheduler.Entity.Term;
import com.example.studentscheduler.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CourseAdd extends AppCompatActivity{

    TextView courseId;
    EditText courseTitle;
    Button startDate;
    Button endDate;
    Spinner statusSpinner;
    EditText instructor;
    EditText email;
    EditText phone;
    TextView termTitle;
    Spinner termSpinner;
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
    String tTitle;
    String tId;
    private Course maxCourse;
    private Term mTerm;
    private List<Term> termList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_add);
        Repository repo = new Repository(getApplication());

        courseId = findViewById(R.id.textView_courseId);
        courseTitle = findViewById(R.id.editTxt_courseTitle);
        startDate = findViewById(R.id.btn_courseStart);
        endDate = findViewById(R.id.btn_courseEnd);
        termSpinner = findViewById(R.id.spn_addTermList);
        termTitle = findViewById(R.id.textView_addTermTitle);
        statusSpinner = findViewById(R.id.spn_addCourseStatus);
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

        //TODO: Implement display list of terms in spinner or other object
/*
        termSpinner = findViewById(R.id.spn_addTermList);
        List<Term> terms = repo.getAllTerms();
        final TermAdapter adapter = new TermAdapter(this);
        termSpinner.setAdapter((SpinnerAdapter) adapter);
        adapter.setTermList(terms);
 */

        instructor = findViewById(R.id.editText_name);
        email = findViewById(R.id.editText_email);
        phone = findViewById(R.id.editText_phone);
        courseDelete = findViewById(R.id.btn_deleteCourse);
        courseSave = findViewById(R.id.btn_saveCourse);

        int cId = repo.getMaxCourseId();
        id = Integer.toString(cId+1);
        courseId.setText(id);
        courseTitle.setText(cTitle);
        instructor.setText(instructorName);
        email.setText(instructorEmail);
        phone.setText(instructorPhone);

        termList = repo.getAllTerms();
        ArrayAdapter<Term> termAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, termList);
        termAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        termSpinner.setAdapter(termAdapter);
        String sId = "";
        do {
            int i = 0;
            sId = termSpinner.getItemAtPosition(i).toString();
            if (sId.equals(tId)){
                termSpinner.setSelection(i);
                mTerm = repo.getTermInfo(Integer.parseInt(sId));
                tTitle = mTerm.getTermTitle();
                termTitle.setText(tTitle);
            }
        } while(!sId.equals(tId));
        termSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String stringId = adapterView.getItemAtPosition(i).toString();
                mTerm = repo.getTermInfo(Integer.parseInt(stringId));
                tTitle = mTerm.getTermTitle();
                termTitle.setText(tTitle);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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
                new DatePickerDialog(CourseAdd.this, sDate, sCalendar.get(Calendar.YEAR),
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
                new DatePickerDialog(CourseAdd.this, eDate, eCalendar.get(Calendar.YEAR),
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

    public void saveCourse(){
        //TODO: Save course & check if there is a course that already exists with the chosen title
    }
}
