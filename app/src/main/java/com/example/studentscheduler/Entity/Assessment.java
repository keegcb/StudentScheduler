package com.example.studentscheduler.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


import java.time.LocalDate;

@Entity(tableName = "Assessment")
public class Assessment {
    @PrimaryKey(autoGenerate = true)
    private String title;
    private int courseId;
    private int type;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean alert;


    @Override
    public String toString() {
        return "Assessment{" +
                "title='" + title + '\'' +
                ", courseId=" + courseId +
                ", type=" + type +
                ", endDate=" + endDate +
                '}';
    }

    public Assessment(String title, int courseId, int type, LocalDate startDate, LocalDate endDate, boolean alert) {
        this.title = title;
        this.courseId = courseId;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.alert = alert;
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
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

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }


}
