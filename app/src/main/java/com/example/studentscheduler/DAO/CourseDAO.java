package com.example.studentscheduler.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.studentscheduler.Entity.Course;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CourseDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertCourse(Course course);

    @Update
    void updateCourse(Course course);

    @Delete
    void deleteCourse(Course course);

    @Query("SELECT * FROM Courses ORDER BY courseId ASC")
    List<Course> getAllCourses();

    @Query("SELECT * FROM Courses WHERE courseId= :courseId ORDER BY courseId ASC")
    Course getCourseInfo(int courseId);

    @Query("SELECT MAX(courseId) FROM Courses")
    int getMaxCourseId();

    @Query("SELECT * FROM Courses WHERE termId= :termId")
    List<Course> termCourse(int termId);

    @Query("SELECT * FROM Courses WHERE courseTitle= :courseT")
    Course getByTitle(String courseT);
}
