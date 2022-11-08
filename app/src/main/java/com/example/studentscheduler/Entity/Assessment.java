package com.example.studentscheduler.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "Assessment")
public class Assessment {
    @PrimaryKey(autoGenerate = true)
    private int assessmentId;

    private String title;
    private int courseId;
    private String type;
    private Date startDate;
    private Date endDate;
    private boolean alert;


    @Override
    public String toString() {
        return assessmentId + " " + title;
    }

    public Assessment(String title, int cId, String type, Date startDate, Date endDate, boolean alert) {
        this.title = title;
        this.courseId = cId;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.alert = alert;
    }

    public Assessment(){

    }

    public int getAssessmentId() {
        return assessmentId;
    }

    public String getTitle() {
        return title;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getType() { return type; }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setAssessmentId(int id){this.assessmentId = id;}

    public boolean getAlert(){return alert;}

    public void setAlert(boolean alert){this.alert = alert;}

}
