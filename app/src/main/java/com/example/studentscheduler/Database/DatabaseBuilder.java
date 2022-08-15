package com.example.studentscheduler.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.studentscheduler.DAO.AssessmentDAO;
import com.example.studentscheduler.DAO.CourseDAO;
import com.example.studentscheduler.DAO.TermDAO;
import com.example.studentscheduler.Entity.Assessment;
import com.example.studentscheduler.Entity.Course;
import com.example.studentscheduler.Entity.Term;


@Database(entities = {Assessment.class, Course.class, Term.class}, version=1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class DatabaseBuilder extends RoomDatabase {
    public abstract AssessmentDAO assessmentDAO();

    public abstract CourseDAO courseDAO();

    public abstract TermDAO termDAO();

    private static volatile DatabaseBuilder INSTANCE;

    static DatabaseBuilder getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DatabaseBuilder.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DatabaseBuilder.class, "studentSchedulerDatabase.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
