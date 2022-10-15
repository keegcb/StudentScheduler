package com.example.studentscheduler.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.studentscheduler.Database.DateConverter;
import com.example.studentscheduler.Database.Repository;
import com.example.studentscheduler.Entity.Course;
import com.example.studentscheduler.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AssessmentDetails extends AppCompatActivity {

    TextView assessmentId;
    EditText assessmentTitle;
    TextView assCourseId;
    Button assCourseTitle;
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
    private Course mCourse;
    Repository repo = new Repository(getApplication());
    Context mContex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_details);

        assessmentId = findViewById(R.id.textView_assessmentId);
        assessmentTitle = findViewById(R.id.editText_assessmentTitle);
        assCourseId = findViewById(R.id.textView_assessmentCourseId);
        assCourseTitle = findViewById(R.id.btn_assessmentCourseName);
        typeSpinner = findViewById(R.id.spn_termList);
        startDate = findViewById(R.id.btn_assStartDate);
        endDate = findViewById(R.id.btn_assEndDate);

        id = getIntent().getStringExtra("id");
        aTitle = getIntent().getStringExtra("title");
        cId = getIntent().getStringExtra("courseId");

        mCourse = repo.getCourseInfo(Integer.parseInt(cId));
        cTitle = mCourse.getCourseTitle();

        type = getIntent().getStringExtra("type");
        sD = getIntent().getLongExtra("start", DateConverter.toTimestamp(new Date()));
        eD = getIntent().getLongExtra("end", DateConverter.toTimestamp(new Date()));

        assessmentId.setText(id);
        assessmentTitle.setText(aTitle);
        assCourseId.setText(cId);
        assCourseTitle.setText(cTitle);
    //TODO: Populate and display spinner for assessment type

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
                new DatePickerDialog(AssessmentDetails.this, sDate, sCalendar.get(Calendar.DAY_OF_MONTH),
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
                new DatePickerDialog(AssessmentDetails.this, eDate, eCalendar.get(Calendar.DAY_OF_MONTH),
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

    public void deleteAssessment(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContex);
            builder.setCancelable(true);
            builder.setMessage("Are you sure you want to delete this assessment?");
            builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                repo.deleteAssessment(repo.getAssessmentInfo(Integer.parseInt(id)));
                }
            });
            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
    }
}