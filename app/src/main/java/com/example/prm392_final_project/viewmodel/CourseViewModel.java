package com.example.prm392_final_project.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.prm392_final_project.model.Course;
import com.example.prm392_final_project.repository.CourseRepository;

import java.util.List;

public class CourseViewModel extends ViewModel {
    private final CourseRepository courseRepository;

    public CourseViewModel() {
        courseRepository = new CourseRepository();
    }

    public LiveData<List<Course>> getCourses() {
        return courseRepository.getCourses();
    }
}