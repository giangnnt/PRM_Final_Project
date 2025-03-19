package com.example.prm392_final_project.Repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.prm392_final_project.Api.ApiResponse;
import com.example.prm392_final_project.Api.CourseApi;
import com.example.prm392_final_project.DataModels.Course;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CourseRepository {
    private static final String BASE_URL = "https://devkid.online/";
    private final CourseApi courseApi;

    public interface CourseCallback {
        void onSuccess(List<Course> courses);
        void onError(String errorMessage);
    }

    public CourseRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        courseApi = retrofit.create(CourseApi.class);
    }

    public void fetchCourses(CourseCallback callback) {
        courseApi.getCourses().enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getResult() != null) {
                    callback.onSuccess(response.body().getResult().getData());
                } else {
                    callback.onError("Error: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {
                callback.onError("Failed to load courses: " + t.getMessage());
                Log.e("API_ERROR", t.getMessage());
            }
        });
    }
}