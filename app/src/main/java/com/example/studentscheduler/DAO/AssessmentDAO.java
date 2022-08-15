package com.example.studentscheduler.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.studentscheduler.Entity.Assessment;

import java.util.List;

@Dao
public interface AssessmentDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAss(Assessment assessment);

    @Update
    void updateAss(Assessment assessment);

    @Delete
    void deleteAss(Assessment assessment);

    @Query("SELECT * FROM Assessment ORDER BY assessmentId ASC")
    List<Assessment> getAllAssessments();
}
