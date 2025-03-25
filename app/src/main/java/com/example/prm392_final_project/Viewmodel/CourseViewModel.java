package com.example.prm392_final_project.Viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.prm392_final_project.Model.Course;
import com.example.prm392_final_project.Repository.CourseRepository;

import java.util.List;
import java.util.UUID;

public class CourseViewModel extends ViewModel {
    private final CourseRepository courseRepository;

    public CourseViewModel() {
        courseRepository = new CourseRepository();
    }

    public LiveData<List<Course>> getCourses() {
        return courseRepository.getCourses();
    }

    public LiveData<Course> getCourseById(UUID courseId) {
        return courseRepository.getCourseById(courseId);
    }

}