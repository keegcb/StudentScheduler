package com.example.studentscheduler.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.studentscheduler.Database.DateConverter;
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
      // cTitle = getIntent().getStringExtra("courseTitle");
        type = getIntent().getStringExtra("type");
        sD = getIntent().getLongExtra("start", DateConverter.toTimestamp(new Date()));
        eD = getIntent().getLongExtra("end", DateConverter.toTimestamp(new Date()));

        assessmentId.setText(id);
        assessmentTitle.setText(aTitle);
        assCourseId.setText(cId);
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
}