package com.example.studentlist;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// below line is for setting table name.
@Entity(tableName = "course_table")
public class Student {

    // below line is to auto increment
    // id for each course.
    @PrimaryKey(autoGenerate = true)

    // variable for our id.
    private int id;

    // below line is a variable
    // for course name.
    private String studentName;

    // below line is use for
    // course description.
    /*private String studentAge;*/
    private int studentAge;
    // below line is use
    // for course duration.
/*
    private String studentGrade;
*/
    private int studentGrade;

    private int studentMarks;

    private boolean dayScholar;

    private boolean meritStudent;

    public Student(/*int id,*/ String studentName, int studentAge, int studentGrade, int studentMarks, boolean dayScholar, boolean meritStudent) {
        /*this.id = id;*/
        this.studentName = studentName;
        this.studentAge = studentAge;
        this.studentGrade = studentGrade;
        this.studentMarks = studentMarks;
        this.dayScholar = dayScholar;
        this.meritStudent = meritStudent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(int studentAge) {
        this.studentAge = studentAge;
    }

    public int getStudentGrade() {
        return studentGrade;
    }

    public void setStudentGrade(int studentGrade) {
        this.studentGrade = studentGrade;
    }

    public int getStudentMarks() {
        return studentMarks;
    }

    public void setStudentMarks(int studentMarks) {
        this.studentMarks = studentMarks;
    }

    public boolean isDayScholar() {
        return dayScholar;
    }

    public void setDayScholar(boolean dayScholar) {
        this.dayScholar = dayScholar;
    }

    public boolean isMeritStudent() {
        return meritStudent;
    }

    public void setMeritStudent(boolean meritStudent) {
        this.meritStudent = meritStudent;
    }

    // below line we are creating constructor class.
    // inside constructor class we are not passing
    // our id because it is incrementing automatically
   /* public Student(String studentName, String studentAge, String studentGrade) {
        this.studentName = studentName;
        this.studentAge = studentAge;
        this.studentGrade = studentGrade;
    }*/

    // on below line we are creating
    // getter and setter methods.
    /*public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(String studentAge) {
        this.studentAge = studentAge;
    }

    public String getStudentGrade() {
        return studentGrade;
    }

    public void setStudentGrade(String studentGrade) {
        this.studentGrade = studentGrade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }*/
}
