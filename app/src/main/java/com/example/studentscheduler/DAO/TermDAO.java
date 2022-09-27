package com.example.studentscheduler.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.studentscheduler.Entity.Term;

import java.util.List;

@Dao
public interface TermDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertTerm(Term term);

    @Update
    void updateTerm(Term term);

    @Delete
    void deleteTerm(Term term);

    @Query("SELECT * FROM Terms ORDER BY termId ASC")
    List<Term> getAllTerms();
}