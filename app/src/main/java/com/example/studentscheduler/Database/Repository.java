package com.example.studentscheduler.Database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.studentscheduler.DAO.AssessmentDAO;
import com.example.studentscheduler.DAO.CourseDAO;
import com.example.studentscheduler.DAO.TermDAO;
import com.example.studentscheduler.Entity.Assessment;
import com.example.studentscheduler.Entity.Course;
import com.example.studentscheduler.Entity.Term;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private final AssessmentDAO mAssessmentDAO;
    private final CourseDAO mCourseDAO;
    private final TermDAO mTermDAO;
    private LiveData<List<Assessment>> mAllAssessments;
    private LiveData<List<Course>> mAllCourses;
    private LiveData<List<Term>> mAllTerms;

    private static final int NUMBER_OF_THREADS =4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application){
        DatabaseBuilder db=DatabaseBuilder.getDatabase(application);
        mAssessmentDAO=db.assessmentDAO();
        mCourseDAO=db.courseDAO();
        mTermDAO=db.termDAO();
    }

    public void insertAssessment(Assessment assessment){
        databaseExecutor.execute(()->{
            mAssessmentDAO.insertAssessment(assessment);
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void updateAssessment(Assessment assessment){
        databaseExecutor.execute(()->{
            mAssessmentDAO.updateAssessment(assessment);
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void deleteAssessment(Assessment assessment){
        databaseExecutor.execute(()->{
            mAssessmentDAO.deleteAssessment(assessment);
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void insertCourse(Course course){
        databaseExecutor.execute(()->{
            mCourseDAO.insertCourse(course);
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void updateCourse(Course course){
        databaseExecutor.execute(()->{
            mCourseDAO.updateCourse(course);
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void deleteCourse(Course course){
        databaseExecutor.execute(()->{
            mCourseDAO.deleteCourse(course);
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void insertTerm(Term term){
        databaseExecutor.execute(()->{
            mTermDAO.insertTerm(term);
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void updateTerm(Term term){
        databaseExecutor.execute(()->{
            mTermDAO.updateTerm(term);
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void deleteTerm(Term term){
        databaseExecutor.execute(()->{
            mTermDAO.deleteTerm(term);
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public List<Assessment> getAllAssessments(){
        databaseExecutor.execute(()->{

        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Course> getAllCourses(){
        databaseExecutor.execute(()->{

        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        return null;
    }

    public LiveData<List<Term>> getAllTerms(){
        databaseExecutor.execute(()->{
            mAllTerms = mTermDAO.getAllTerms();
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllTerms;
    }
}
