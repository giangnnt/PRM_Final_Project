package com.example.prm392_final_project.api;


import com.example.prm392_final_project.model.Course;
import com.example.prm392_final_project.model.ResponseModel;
import com.example.prm392_final_project.model.ResultWrapper;
import com.example.prm392_final_project.model.User;
import com.example.prm392_final_project.model.auth.LoginRequest;
import com.example.prm392_final_project.model.auth.RegisterRequest;
import com.example.prm392_final_project.model.auth.TokenData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface ApiService {
    // AUTH
    @Headers({"Content-Type: application/json"})
    @POST("api/auth/login")
    Call<ResponseModel<TokenData>> login(@Body LoginRequest loginRequest);

    @Headers({"Content-Type: application/json"})
    @POST("api/auth/register")
    Call<ResponseModel> register(@Body RegisterRequest registerRequest);

    // USER
    @GET("api/users/own")
    Call<ResponseModel<User>> getUser(@Header("Authorization") String token);

    // COURSE
    @Headers({"accept: */*"})
    @GET("api/courses/summary")
    Call<ResponseModel<List<Course>>> getCourses();
}
