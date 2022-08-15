package com.example.studentscheduler.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.studentscheduler.Entity.Assessment;

@Dao
public interface AssessmentDAO extends GenericDao{
    @Insert(onClick = OnConflictStrategy.REPLACE){
        void insert(Assessment assessment);
    }
}
