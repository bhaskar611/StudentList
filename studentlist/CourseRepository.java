package com.example.studentlist;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CourseRepository {

    // below line is the create a variable
    // for dao and list for all courses.
    private Dao dao;
    private LiveData<List<Student>> allCourses;
    private LiveData<List<Student>> studentswithLessMarks;
    private LiveData<List<Student>> studentswithMoreMarks;
    private LiveData<List<Student>> studentsFromLowerGrade;
    private LiveData<List<Student>> studentsFromMiddleGrade;
    private LiveData<List<Student>> studentsFromUpperGrade;
    private LiveData<List<Student>> studentsFromDayScholor;
    private LiveData<List<Student>> studentsFromMerit;



    // creating a constructor for our variables
    // and passing the variables to it.
    public CourseRepository(Application application) {
        CourseDatabase database = CourseDatabase.getInstance(application);
        dao = database.Dao();
        allCourses = dao.getAllCourses();
        studentswithLessMarks = dao.getStudentsLessMarks();
        studentswithMoreMarks = dao.getStudentsMoreMarks();
        studentsFromDayScholor = dao.getStudentsFromDayScholor();
        studentsFromMerit = dao.getMeritStudent();
        studentsFromLowerGrade = dao.getStudentsFrom1to5();
        studentsFromMiddleGrade = dao.getStudentsFrom6to10();
        studentsFromUpperGrade = dao.getStudentsFrom11to12();



    }

    // creating a method to insert the data to our database.
    public void insert(Student model) {
        new InsertCourseAsyncTask(dao).execute(model);
    }

    // creating a method to update data in database.
    public void update(Student model) {
        new UpdateCourseAsyncTask(dao).execute(model);
    }

    // creating a method to delete the data in our database.
    public void delete(Student model) {
        new DeleteCourseAsyncTask(dao).execute(model);
    }

    // below is the method to delete all the courses.
    public void deleteAllCourses() {
        new DeleteAllCoursesAsyncTask(dao).execute();
    }

    // below method is to read all the courses.
    public LiveData<List<Student>> getAllCourses() {
        return allCourses;
    }

    public LiveData<List<Student>> getStudentsLowMarks() {
        return studentswithLessMarks;
    }
    public LiveData<List<Student>> getStudentsMoreMarks() {
        return studentswithMoreMarks;
    }

    public LiveData<List<Student>> getStudentsFromDayScholor() {
        return studentsFromDayScholor;
    }
    public LiveData<List<Student>> getStudentsFromMerit() {
        return studentsFromMerit;
    }
    public LiveData<List<Student>> getStudentsLowerGrade() {
        return studentsFromLowerGrade;
    }
    public LiveData<List<Student>> getStudentsMiddleGrade() {
            return studentsFromMiddleGrade;
    }
    public LiveData<List<Student>> getStudentsUpperGrade() {
        return studentsFromUpperGrade;
    }




    // we are creating a async task method to insert new course.
    private static class InsertCourseAsyncTask extends AsyncTask<Student, Void, Void> {
        private Dao dao;

        private InsertCourseAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Student... model) {
            // below line is use to insert our modal in dao.
            dao.insert(model[0]);
            return null;
        }
    }

    // we are creating a async task method to update our course.
    private static class UpdateCourseAsyncTask extends AsyncTask<Student, Void, Void> {
        private Dao dao;

        private UpdateCourseAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Student... models) {
            // below line is use to update
            // our modal in dao.
            dao.update(models[0]);
            return null;
        }
    }

    // we are creating a async task method to delete course.
    private static class DeleteCourseAsyncTask extends AsyncTask<Student, Void, Void> {
        private Dao dao;

        private DeleteCourseAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Student... models) {
            // below line is use to delete
            // our course modal in dao.
            dao.delete(models[0]);
            return null;
        }
    }

    // we are creating a async task method to delete all courses.
    private static class DeleteAllCoursesAsyncTask extends AsyncTask<Void, Void, Void> {
        private Dao dao;
        private DeleteAllCoursesAsyncTask(Dao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            // on below line calling method
            // to delete all courses.
            dao.deleteAllCourses();
            return null;
        }
    }
}