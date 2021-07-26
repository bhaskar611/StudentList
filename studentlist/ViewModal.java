package com.example.studentlist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModal extends AndroidViewModel {

    // creating a new variable for course repository.
    private CourseRepository repository;

    // below line is to create a variable for live
    // data where all the courses are present.
    private LiveData<List<Student>> allCourses;
    private LiveData<List<Student>> studentswithLessMarks;
    private LiveData<List<Student>> studentswithMoreMarks;
    private LiveData<List<Student>> studentsFromLowerGrade;
    private LiveData<List<Student>> studentsFromMiddleGrade;
    private LiveData<List<Student>> studentsFromUpperGrade;
    private LiveData<List<Student>> studentsFromDayScholor;
    private LiveData<List<Student>> studentsFromMerit;





    // constructor for our view modal.
    public ViewModal(@NonNull Application application) {
        super(application);
        repository = new CourseRepository(application);
        allCourses = repository.getAllCourses();
        studentswithLessMarks = repository.getStudentsLowMarks();
        studentswithMoreMarks = repository.getStudentsMoreMarks();
        studentsFromDayScholor = repository.getStudentsFromDayScholor();
        studentsFromMerit = repository.getStudentsFromMerit();
        studentsFromLowerGrade = repository.getStudentsLowerGrade();
        studentsFromMiddleGrade = repository.getStudentsMiddleGrade();
        studentsFromUpperGrade = repository.getStudentsUpperGrade();



    }

    // below method is use to insert the data to our repository.
    public void insert(Student model) {
        repository.insert(model);
    }

    // below line is to update data in our repository.
    public void update(Student model) {
        repository.update(model);
    }

    // below line is to delete the data in our repository.
    public void delete(Student model) {
        repository.delete(model);
    }

    // below method is to delete all the courses in our list.
    public void deleteAllCourses() {
        repository.deleteAllCourses();
    }

    // below method is to get all the courses in our list.
    public LiveData<List<Student>> getAllCourses() {
        return allCourses;
    }

    public LiveData<List<Student>> getStudentsLowMarks() {
        return studentswithLessMarks;
    }

    public LiveData<List<Student>> getStudentsMoreMarks() {
        return studentswithMoreMarks;
    }

    public LiveData<List<Student>> getStudentsDayScholor() {
        return studentsFromDayScholor;
    }
    public LiveData<List<Student>> getStudentsFromMerit() {
        return studentsFromMerit;
    }
    public LiveData<List<Student>> getStudentsFromLowerGrade() {
        return studentsFromLowerGrade;
    }
    public LiveData<List<Student>> getStudentsFromMiddleGrade() {
        return studentsFromMiddleGrade;
    }
    public LiveData<List<Student>> getStudentsFromUpperGrade() {
        return studentsFromUpperGrade;
    }





}
