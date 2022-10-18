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
import com.example.studentscheduler.Entity.Assessment;
import com.example.studentscheduler.Entity.Course;
import com.example.studentscheduler.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AssessmentAdd extends AppCompatActivity {

    TextView assessmentId;
    EditText assessmentTitle;
    TextView assCourseTitle;
    Spinner addCourseSpinner;
    Spinner typeSpinner;
    Button startDate;
    Button endDate;

    DatePickerDialog.OnDateSetListener sDate;
    final Calendar sCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener eDate;
    final Calendar eCalendar = Calendar.getInstance();

    String id;
    String aTitle;
    String cId;
    String cTitle;
    String type;
    long sD;
    long eD;
    Repository repo = new Repository(getApplication());
    private Assessment maxAssessment;
    private Course mCourse;
    private List<Course> courseList;
    String mFormat = "MM/dd/yy";
    SimpleDateFormat sdf = new SimpleDateFormat(mFormat, Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_add);


        assessmentId = findViewById(R.id.textView_addAssessmentId);
        assessmentTitle = findViewById(R.id.editText_addAssessmentTitle);
        assCourseTitle = findViewById(R.id.textView_addAssessmentCourseTitle);
        addCourseSpinner = findViewById(R.id.spn_addAssessmentCourseId);
        typeSpinner = findViewById(R.id.spn_addTermList);
        startDate = findViewById(R.id.btn_addAssStartDate);
        endDate = findViewById(R.id.btn_addAssEndDate);

        int assId = repo.getMaxAssessmentId();
        id = Integer.toString(assId+1);
        assessmentId.setText(id);

        courseList = repo.getAllCourses();
        ArrayAdapter<Course> courseAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, courseList);
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        addCourseSpinner.setAdapter(courseAdapter);
        String sId = "";
        do {
            int i = 0;
            sId = addCourseSpinner.getItemAtPosition(i).toString();
            if (sId.equals(cId)){
                addCourseSpinner.setSelection(i);
                mCourse = repo.getCourseInfo(Integer.parseInt(sId));
                cTitle = mCourse.getCourseTitle();
                assCourseTitle.setText(cTitle);
            }
        } while(!sId.equals(cId));
        addCourseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String stringId = adapterView.getItemAtPosition(i).toString();
                mCourse = repo.getCourseInfo(Integer.parseInt(stringId));
                cTitle = mCourse.getCourseTitle();
                assCourseTitle.setText(cTitle);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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
                new DatePickerDialog(AssessmentAdd.this, sDate, sCalendar.get(Calendar.YEAR),
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
                new DatePickerDialog(AssessmentAdd.this, eDate, eCalendar.get(Calendar.YEAR),
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
        startDate.setText(sdf.format(sCalendar.getTime()));
    }
    public void updateEndDate() {
        endDate.setText(sdf.format(eCalendar.getTime()));
    }

    public void saveAssessment(){
        //TODO: Get value of item from spinner to identify course in DB

    }
}