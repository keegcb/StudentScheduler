package com.example.studentscheduler.Database;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.studentscheduler.DAO.AssessmentDAO;
import com.example.studentscheduler.DAO.CourseDAO;
import com.example.studentscheduler.DAO.TermDAO;
import com.example.studentscheduler.Entity.Assessment;
import com.example.studentscheduler.Entity.Course;
import com.example.studentscheduler.Entity.Term;

import java.sql.Date;
import java.time.Instant;


@Database(entities = {Assessment.class, Course.class, Term.class}, version=3, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class DatabaseBuilder extends RoomDatabase {
    public abstract AssessmentDAO assessmentDAO();

    public abstract CourseDAO courseDAO();

    public abstract TermDAO termDAO();

    private static volatile DatabaseBuilder INSTANCE;

    public static synchronized DatabaseBuilder getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DatabaseBuilder.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DatabaseBuilder.class, "studentSchedulerDatabase.db")
                            .fallbackToDestructiveMigration().allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase sqlDB){
            super.onCreate(sqlDB);
            new InputData(INSTANCE).execute();
        }
    };

    private static class InputData extends AsyncTask<Void, Void, Void> {
        private TermDAO termDAO;
        private CourseDAO courseDAO;
        private AssessmentDAO assessmentDAO;

        public InputData(DatabaseBuilder db){
            termDAO = db.termDAO();
            courseDAO = db.courseDAO();
            assessmentDAO = db.assessmentDAO();
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        protected Void doInBackground(Void... voids) {
            termDAO.insertTerm(new Term("Fall Term", Date.from(Instant.now()), Date.from(Instant.now())));
            termDAO.insertTerm(new Term("Winter Term", Date.from(Instant.now()), Date.from(Instant.now())));
            termDAO.insertTerm(new Term("Spring Term", Date.from(Instant.now()), Date.from(Instant.now())));

            //TODO: Add Course and Assessment Data to database
            return null;
        }
    }

}
