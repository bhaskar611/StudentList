package com.example.studentlist;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
// Adding annotation
// to our Dao class
@androidx.room.Dao
public interface Dao {
    // below method is use to
    // add data to database.
    @Insert
    void insert(Student model);

    // below method is use to update
    // the data in our database.
    @Update
    void update(Student model);

    // below line is use to delete a
    // specific course in our database.
    @Delete
    void delete(Student model);

    // on below line we are making query to
    // delete all courses from our database.
    @Query("DELETE FROM course_table")
    void deleteAllCourses();

    // below line is to read all the courses from our database.
    // in this we are ordering our courses in ascending order
    // with our course name.
    @Query("SELECT * FROM course_table ORDER BY studentName ASC")
    LiveData<List<Student>> getAllCourses();

    @Query("select * FROM course_table  where studentMarks  <= 300 ")
    LiveData<List<Student>> getStudentsLessMarks();

    @Query("select * FROM course_table  where studentMarks  <=  400")
    LiveData<List<Student>> getStudentsMoreMarks();

    @Query("select * FROM course_table  where dayScholar  == 1")
    LiveData<List<Student>> getStudentsFromDayScholor();

    @Query("select * FROM course_table  where meritStudent  == 1")
    LiveData<List<Student>> getMeritStudent();

    @Query("select * FROM course_table  where studentGrade <=5")
    LiveData<List<Student>> getStudentsFrom1to5();

    @Query("select * FROM course_table  where studentGrade  >=6  AND studentGrade <=10")
    LiveData<List<Student>> getStudentsFrom6to10();

    @Query("select * FROM course_table  where studentGrade  >=11 AND studentGrade <=12")
    LiveData<List<Student>> getStudentsFrom11to12();

}
