package com.example.studentscheduler.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AssessmentDetails extends AppCompatActivity {

    TextView assessmentId;
    EditText assessmentTitle;
    TextView assCourseTitle;
    Spinner courseSpinner;
    RadioGroup typeRadio;
    RadioButton performance;
    RadioButton objective;
    Button startDate;
    Button endDate;

    DatePickerDialog.OnDateSetListener sDate;
    final Calendar sCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener eDate;
    final Calendar eCalendar = Calendar.getInstance();

    private String id;
    private String aTitle;
    private String cId;
    private String cTitle;
    private String type;
    private long sD;
    private long eD;
    private Course mCourse;
    Repository repo = new Repository(getApplication());
    Context mContex;
    private List<Course> courseList;
    String mFormat = "MM/dd/yy";
    SimpleDateFormat sdf = new SimpleDateFormat(mFormat, Locale.US);

    private String nTitle;
    private int nCourseId;
    private String nType;
    private Date nStartDate;
    private Date nEndDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_details);

        assessmentId = findViewById(R.id.textView_assessmentId);
        assessmentTitle = findViewById(R.id.editText_assessmentTitle);
        assCourseTitle = findViewById(R.id.textView_assessmentCourseTitle);
        courseSpinner = findViewById(R.id.spn_assessmentCourseId);
        typeRadio = findViewById(R.id.radioGroup_type);
        performance = findViewById(R.id.radio_performance);
        objective = findViewById(R.id.radio_objective);
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
        assCourseTitle.setText(cTitle);

        if(type.equals("Performance")){
            performance.setChecked(true);
        }
        if(type.equals("Objective")){
            objective.setChecked(true);
        }


        courseList = repo.getAllCourses();
        ArrayAdapter<Course> courseAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, courseList);
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        courseSpinner.setAdapter(courseAdapter);
        String sId = "";
        int i = 0;
        do {
            sId = courseSpinner.getItemAtPosition(i).toString();
            if (sId.equals(cId)){
                courseSpinner.setSelection(i);
                mCourse = repo.getCourseInfo(Integer.parseInt(sId));
                cTitle = mCourse.getCourseTitle();
                assCourseTitle.setText(cTitle);
            }
            i++;
        } while(!sId.equals(cId));
        courseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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

                String info = startDate.getText().toString();
                try{
                    Date date = sdf.parse(info);
                    sCalendar.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(AssessmentDetails.this, sDate, sCalendar.get(Calendar.YEAR),
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
                new DatePickerDialog(AssessmentDetails.this, eDate, eCalendar.get(Calendar.YEAR),
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

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_share_notify, menu);
        return true;
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

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Text from note field");
                sendIntent.putExtra(Intent.EXTRA_TITLE, "Message Title");
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                return true;
            case R.id.notify:
                String dateFromScreen = startDate.getText().toString();
                Date mStart = null;
                try{
                    mStart = sdf.parse(dateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //TODO: Finish creating receiver
                Long trigger = mStart.getTime();
                Intent intent = new Intent(AssessmentDetails.this, MyReceiver.class);
                intent.putExtra("key", "Your course [" + id + " " + aTitle + "] starts today.");
                PendingIntent sender = PendingIntent.getBroadcast(AssessmentDetails.this, MainActivity.numAlert++, intent, 0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void deleteAssessment(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);
            builder.setMessage("Are you sure you want to delete this assessment?");
            builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                repo.deleteAssessment(repo.getAssessmentInfo(Integer.parseInt(id)));
                finish();
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

    public void saveAssessment(View view){
        nStartDate = sCalendar.getTime();
        nEndDate = eCalendar.getTime();

        //TODO: Fix update so it updates the specific term in the DB based on ID
        if (checkValues()) {
            Assessment nAssessment = new Assessment(nTitle, nCourseId, nType, nStartDate, nEndDate, false);
            nAssessment.setAssessmentId(Integer.parseInt(id));
            repo.updateAssessment(nAssessment);
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