package com.example.prm392_final_project;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392_final_project.Adapter.CourseAdapter;
import com.example.prm392_final_project.DataModels.Course;
import com.example.prm392_final_project.Repository.CourseRepository;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CourseAdapter courseAdapter;
    private CourseRepository courseRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        courseRepository = new CourseRepository();
        fetchCourses();
    }

    private void fetchCourses() {
        courseRepository.fetchCourses(new CourseRepository.CourseCallback() {
            @Override
            public void onSuccess(List<Course> courses) {
                courseAdapter = new CourseAdapter(courses);
                recyclerView.setAdapter(courseAdapter);
            }

            @Override
            public void onError(String errorMessage) {
                Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}