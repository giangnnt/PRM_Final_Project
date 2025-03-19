package com.example.prm392_final_project.Api;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CourseApi {
    @GET("api/courses/summary")
    Call<ApiResponse> getCourses();
}


