package com.example.studentscheduler.Database;

import android.app.Application;

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
    private AssessmentDAO mAssessmentDAO;
    private CourseDAO mCourseDAO;
    private TermDAO mTermDAO;
    private List<Assessment> mAllAssessments;
    private List<Course> mAllCourses;
    private List<Term> mAllTerms;

    private static final int NUMMBER_OF_THREADS=4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMMBER_OF_THREADS);

    public Repository(Application application){
        DatabaseBuilder db=DatabaseBuilder.getDatabase(application);
        mAssessmentDAO=db.assessmentDAO();
        mCourseDAO=db.courseDAO();
        mTermDAO=db.termDAO();
    }

    public void insert(Assessment assessment){
        databaseExecutor.execute(()->{
            mAssessmentDAO.insertAss(assessment);
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void update(Assessment assessment){
        databaseExecutor.execute(()->{
            mAssessmentDAO.updateAss(assessment);
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void delete(Assessment assessment){
        databaseExecutor.execute(()->{
            mAssessmentDAO.deleteAss(assessment);
        });
        try{
            Thread.sleep(1000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void insert(Course course){
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

    public void update(Course course){
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

    public void delete(Course course){
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

    public void insert(Term term){
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

    public List<Assessment> getmAllAssessments(){
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
}
