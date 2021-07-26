package com.example.studentlist;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    // creating a variables for our recycler view.
    private RecyclerView coursesRV;
    private static final int ADD_COURSE_REQUEST = 1;
    private static final int EDIT_COURSE_REQUEST = 2;
    private ViewModal viewmodal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing our variable for our recycler view and fab.
        coursesRV = findViewById(R.id.idRVCourses);
        FloatingActionButton fab = findViewById(R.id.idFABAdd);

        // adding on click listener for floating action button.
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // starting a new activity for adding a new course
                // and passing a constant value in it.
                Intent intent = new Intent(MainActivity.this, NewCourseActivity.class);
                startActivityForResult(intent, ADD_COURSE_REQUEST);
            }
        });

        // setting layout manager to our adapter class.
        coursesRV.setLayoutManager(new LinearLayoutManager(this));
        coursesRV.setHasFixedSize(true);

        // initializing adapter for recycler view.
        final CourseRVAdapter adapter = new CourseRVAdapter();

        // setting adapter class for recycler view.
        coursesRV.setAdapter(adapter);

        // passing a data from view modal.
        viewmodal = ViewModelProviders.of(this).get(ViewModal.class);

        // below line is use to get all the courses from view modal.
        viewmodal.getAllCourses().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> models) {
                // when the data is changed in our models we are
                // adding that list to our adapter class.
                adapter.submitList(models);
            }
        });
        // below method is use to add swipe to delete method for item of recycler view.
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // on recycler view item swiped then we are deleting the item of our recycler view.
                viewmodal.delete(adapter.getCourseAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Course deleted", Toast.LENGTH_SHORT).show();
            }
        }).
                // below line is use to attach this to recycler view.
                        attachToRecyclerView(coursesRV);
        // below line is use to set item click listener for our item of recycler view.
        adapter.setOnItemClickListener(new CourseRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Student model) {
                // after clicking on item of recycler view
                // we are opening a new activity and passing
                // a data to our activity.
                Intent intent = new Intent(MainActivity.this, NewCourseActivity.class);
                intent.putExtra(NewCourseActivity.EXTRA_ID, model.getId());
                intent.putExtra(NewCourseActivity.EXTRA_COURSE_NAME, model.getStudentName());
                intent.putExtra(NewCourseActivity.EXTRA_DESCRIPTION, model.getStudentAge());
                intent.putExtra(NewCourseActivity.EXTRA_DURATION, model.getStudentGrade());
                intent.putExtra(NewCourseActivity.EXTRA_MARKS, model.getStudentMarks());
                intent.putExtra(NewCourseActivity.EXTRA_DAYSCHOLAR, model.isDayScholar());
                intent.putExtra(NewCourseActivity.EXTRA_MERIT, model.isMeritStudent());



                // below line is to start a new activity and
                // adding a edit course constant.
                startActivityForResult(intent, EDIT_COURSE_REQUEST);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        // initializing adapter for recycler view.
        final CourseRVAdapter adapter = new CourseRVAdapter();

        // setting adapter class for recycler view.
        coursesRV.setAdapter(adapter);
        final CharSequence[] items = {"Students from 1 to 5", "Students from 6 to 10", "Students from 10 to 12", "Total < 300","Total < 400","Day Scholor","Merit student"};
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_item_one) {


                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Title");
            builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int item) {
                    switch (item) {
                        case 0:
                            Toast.makeText(MainActivity.this,"Students from 1 to 5",Toast.LENGTH_SHORT).show();
/*
                                    dialogClickListener.onData("button 1");
*/
                            viewmodal.getStudentsFromLowerGrade().observe(MainActivity.this, new Observer<List<Student>>() {
                                @Override
                                public void onChanged(List<Student> models) {
                                    // when the data is changed in our models we are
                                    // adding that list to our adapter class.
                                    adapter.submitList(models);
                                }
                            });
                            break;
                        case 1:
                            Toast.makeText(MainActivity.this,"Students from 6 to 10",Toast.LENGTH_SHORT).show();

/*
                                    dialogClickListener.onData("button 2");
*/
                            viewmodal.getStudentsFromMiddleGrade().observe(MainActivity.this, new Observer<List<Student>>() {
                                @Override
                                public void onChanged(List<Student> models) {
                                    // when the data is changed in our models we are
                                    // adding that list to our adapter class.
                                    adapter.submitList(models);
                                }
                            });

                            break;
                        case 2:
                            Toast.makeText(MainActivity.this,"Students from 11 to 12",Toast.LENGTH_SHORT).show();

/*
                                    dialogClickListener.onData("button 3");
*/
                            viewmodal.getStudentsFromUpperGrade().observe(MainActivity.this, new Observer<List<Student>>() {
                                @Override
                                public void onChanged(List<Student> models) {
                                    // when the data is changed in our models we are
                                    // adding that list to our adapter class.
                                    adapter.submitList(models);
                                }
                            });
                            break;
                        case 3:
                            Toast.makeText(MainActivity.this,"Total < 300",Toast.LENGTH_SHORT).show();

/*
                                    dialogClickListener.onData("button 4");
*/

                            viewmodal.getStudentsLowMarks().observe(MainActivity.this, new Observer<List<Student>>() {
                                @Override
                                public void onChanged(List<Student> models) {
                                    // when the data is changed in our models we are
                                    // adding that list to our adapter class.
                                    adapter.submitList(models);
                                }
                            });
                            break;
                        case 4:
                            Toast.makeText(MainActivity.this,"Total < 400",Toast.LENGTH_SHORT).show();
                            viewmodal.getStudentsMoreMarks().observe(MainActivity.this, new Observer<List<Student>>() {
                                @Override
                                public void onChanged(List<Student> models) {
                                    // when the data is changed in our models we are
                                    // adding that list to our adapter class.
                                    adapter.submitList(models);
                                }
                            });
                            break;
                        case 5:
                            Toast.makeText(MainActivity.this,"Day Scholor",Toast.LENGTH_SHORT).show();
                            viewmodal.getStudentsDayScholor().observe(MainActivity.this, new Observer<List<Student>>() {
                                @Override
                                public void onChanged(List<Student> models) {
                                    // when the data is changed in our models we are
                                    // adding that list to our adapter class.
                                    adapter.submitList(models);
                                }
                            });
                            break;
                        case 6:
                            Toast.makeText(MainActivity.this,"Day Merit",Toast.LENGTH_SHORT).show();
                            viewmodal.getStudentsFromMerit().observe(MainActivity.this, new Observer<List<Student>>() {
                                @Override
                                public void onChanged(List<Student> models) {
                                    // when the data is changed in our models we are
                                    // adding that list to our adapter class.
                                    adapter.submitList(models);
                                }
                            });
                            break;
                    }
/*
                    Toast.makeText(getApplicationContext(), items[item], Toast.LENGTH_SHORT).show();
*/
                }
            });
              /*  builder.setItems(new CharSequence[]
                                {"button 1", "button 2", "button 3", "button 4"},
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // The 'which' argument contains the index position
                                // of the selected item
                                switch (which) {
                                    case 0:
                                        *//*dialogClickListener.onData("button 1");*//*

                                        break;
                                    case 1:
                                      *//*  dialogClickListener.onData("button 2");*//*

                                        break;
                                    case 2:
                                        *//*dialogClickListener.onData("button 3");*//*

                                        break;
                                    case 3:
                                        *//*dialogClickListener.onData("button 4");*//*

                                        break;
                                }
                            }
                        });*/

                // builder.create().show();
                builder
                        .setPositiveButton(
                                "Yes",
                                new DialogInterface
                                        .OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {

                                      /*  dialogClickListener.onPositiveButtonClicked();*/

                                        // When the user click yes button
                                        // then app will close
                                        //finish();
                                        // result.setText("Clicked Yes");

                                        dialog.cancel();
                                    }
                                });

                // Set the Negative button with No name
                // OnClickListener method is use
                // of DialogInterface interface.
                builder
                        .setNegativeButton(
                                "No",
                                new DialogInterface
                                        .OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                       /* dialogClickListener.onNegativeButtonClicked();*/

                                        // If user click no
                                        // then dialog box is canceled.
                                        // result.setText("Clicked No");

                                        dialog.cancel();
                                    }
                                });


                builder.create().show();
            }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_COURSE_REQUEST && resultCode == RESULT_OK) {
            String courseName = data.getStringExtra(NewCourseActivity.EXTRA_COURSE_NAME);
            int courseDescription = data.getIntExtra(NewCourseActivity.EXTRA_DESCRIPTION,0);
            int courseDuration = data.getIntExtra(NewCourseActivity.EXTRA_DURATION,0);
            int studentMarks = data.getIntExtra(NewCourseActivity.EXTRA_MARKS,0);
            boolean isDayScholor = data.getBooleanExtra(NewCourseActivity.EXTRA_DAYSCHOLAR,false);
            boolean isMerit = data.getBooleanExtra(NewCourseActivity.EXTRA_MERIT,false);
            Student model = new Student(courseName, courseDescription, courseDuration,studentMarks,isDayScholor,isMerit);
            viewmodal.insert(model);
            Toast.makeText(this, "Course saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_COURSE_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(NewCourseActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Course can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String courseName = data.getStringExtra(NewCourseActivity.EXTRA_COURSE_NAME);
            int courseDesc = data.getIntExtra(NewCourseActivity.EXTRA_DESCRIPTION,0);
            int courseDuration = data.getIntExtra(NewCourseActivity.EXTRA_DURATION,0);
            int stdMarks = data.getIntExtra(NewCourseActivity.EXTRA_MARKS,0);
            boolean isDayScholor = data.getBooleanExtra(NewCourseActivity.EXTRA_DAYSCHOLAR,false);
            boolean isMerit = data.getBooleanExtra(NewCourseActivity.EXTRA_MERIT,false);

            Student model = new Student(courseName, courseDesc, courseDuration,stdMarks,isDayScholor,isMerit);
            model.setId(id);
            viewmodal.update(model);
            Toast.makeText(this, "Course updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Course not saved", Toast.LENGTH_SHORT).show();
        }
    }
}
