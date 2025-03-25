package com.example.prm392_final_project.Repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.prm392_final_project.Api.ApiService;
import com.example.prm392_final_project.Api.RetrofitClient;
import com.example.prm392_final_project.Model.ResponseModel;
import com.example.prm392_final_project.Model.Course;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseRepository {
    private final ApiService apiService;

    public CourseRepository() {
        apiService = RetrofitClient.getApiService();
    }

    public LiveData<List<Course>> getCourses() {
        MutableLiveData<List<Course>> courseData = new MutableLiveData<>();

        apiService.getCourses().enqueue(new Callback<ResponseModel<List<Course>>>() {
            @Override
            public void onResponse(Call<ResponseModel<List<Course>>> call, Response<ResponseModel<List<Course>>> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getResult() != null
                        && response.body().getResult().getData() != null) {

                    List<Course> courses = response.body().getResult().getData();
                    courseData.setValue(courses);
                    Log.d("COURSE_SUCCESS", "Courses loaded: " + courses.size());
                } else {
                    Log.e("COURSE_ERROR", "Response Failed: " + response.code());
                    courseData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<List<Course>>> call, Throwable t) {
                Log.e("COURSE_ERROR", "API Call Failed: " + t.getMessage());
                courseData.setValue(null);
            }
        });

        return courseData;
    }
    public LiveData<Course> getCourseById(UUID courseId) {
        MutableLiveData<Course> courseData = new MutableLiveData<>();
        apiService.getCourseById(courseId).enqueue(new Callback<ResponseModel<Course>>() {

            @Override
            public void onResponse(Call<ResponseModel<Course>> call, Response<ResponseModel<Course>> response) {

                if (response.isSuccessful() && response.body() != null && response.body().getResult() != null) {
                    Course course = response.body().getResult().getData();
                    courseData.setValue(course);
                    Log.d("COURSE_SUCCESS", "Course loaded: " + course.getName());

                    } else {
                    Log.e("COURSE_ERROR", "Response Failed: " + response.code());
                    courseData.setValue(null);
                    }
            }
            @Override
            public void onFailure(Call<ResponseModel<Course>> call, Throwable t) {
                Log.e("COURSE_ERROR", "API Call Failed: " + t.getMessage());
                courseData.setValue(null);
            }
        });
        return courseData;
    }
}

