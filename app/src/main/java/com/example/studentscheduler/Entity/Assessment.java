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
    private int type;
    private Date startDate;
    private Date endDate;
    private boolean alert;


    @Override
    public String toString() {
        return "Assessment{" +
                "assessmentId='" + assessmentId + '\'' +
                ", title='" + title + '\'' +
                ", courseId=" + courseId +
                ", type=" + type +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", alert=" + alert +
                '}';
    }

    public Assessment(int assessmentId, int assessmentId1, String title, int courseId, int type, Date startDate, Date endDate, boolean alert) {
        this.assessmentId = assessmentId1;
        this.title = title;
        this.courseId = courseId;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.alert = alert;
    }

    public Assessment(){

    }

    public String getTitle() {
        return title;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getType() {
        return type;
    }

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

    public void setType(int type) {
        this.type = type;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    public int getAssessmentId() {
        return assessmentId;
    }

}
