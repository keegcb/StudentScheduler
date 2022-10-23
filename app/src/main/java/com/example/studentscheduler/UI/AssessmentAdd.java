package com.example.studentscheduler.UI;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studentscheduler.Database.DateConverter;
import com.example.studentscheduler.Database.Repository;
import com.example.studentscheduler.Entity.Assessment;
import com.example.studentscheduler.Entity.Course;
import com.example.studentscheduler.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
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
    RadioGroup typeRadio;
    RadioButton performance;
    RadioButton objective;
    Button startDate;
    Button endDate;

    DatePickerDialog.OnDateSetListener sDate;
    final Calendar sCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener eDate;
    final Calendar eCalendar = Calendar.getInstance();

    String id;
    String aTitle;
    String cId = "1";
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

    private String nTitle;
    private int nCourseId;
    private String nType;
    private Date nStartDate;
    private Date nEndDate;

    @RequiresApi(api = Build.VERSION_CODES.O)
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
        int i = 0;
        do {
            sId = addCourseSpinner.getItemAtPosition(i).toString();
            if (sId.equals(cId)){
                addCourseSpinner.setSelection(i);
                mCourse = repo.getCourseInfo(Integer.parseInt(sId));
                cTitle = mCourse.getCourseTitle();
                assCourseTitle.setText(cTitle);
            }
            i++;
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

    public void onSelectRadioButton(View view){
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()){
            case R.id.radio_performance:
                if(checked)
                    type = "Performance";
                break;
            case R.id.radio_objective:
                if(checked)
                    type = "Objective";
                break;
        }
    }

//TODO: Make checks for empty fields before saving assessment
    public void saveAssessment(View view){
        nStartDate = sCalendar.getTime();
        nEndDate = eCalendar.getTime();

        if (checkValues()) {
            Assessment nAssessment = new Assessment(nTitle, nCourseId, nType, nStartDate, nEndDate, false);
            repo.insertAssessment(nAssessment);
            this.finish();
        }
    }

    public boolean checkValues(){
        boolean values = true;
        if (assessmentTitle.getText().toString().equals("")){
            Toast.makeText(this, "Input a title", Toast.LENGTH_LONG).show();
            values = false;
        } else {
            nTitle = assessmentTitle.getText().toString();
        }
        if (cId.equals("")){
            Toast.makeText(this, "Select a course", Toast.LENGTH_LONG).show();
            values = false;
        } else {
            nCourseId = Integer.parseInt(cId);
        }
        if (type.equals("")){
            Toast.makeText(this, "Select a type", Toast.LENGTH_LONG).show();
            values = false;
        } else {
            nType = type;
        }
        return values;
    }
}