package com.example.studentscheduler.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.studentscheduler.Entity.Assessment;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface AssessmentDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAssessment(Assessment assessment);

    @Update
    void updateAssessment(Assessment assessment);

    @Delete
    void deleteAssessment(Assessment assessment);

    @Query("SELECT * FROM Assessment ORDER BY assessmentId ASC")
    List<Assessment> getAllAssessments();

    @Query("SELECT MAX(assessmentId) FROM Assessment")
    int getMaxAssessmentId();

    @Query("SELECT * FROM Assessment WHERE assessmentId= :id")
    Assessment getAssessmentInfo(int id);

    @Query("SELECT * FROM Assessment WHERE courseId= :id")
    List<Assessment> courseAssessment(int id);
}
