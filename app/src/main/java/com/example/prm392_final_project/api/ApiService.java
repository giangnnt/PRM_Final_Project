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
    @Headers({
            "accept: */*",
            "Content-Type: application/json;odata.metadata=minimal;odata.streaming=true"
    })
    @POST("auth/login")
    Call<ResponseModel<TokenData>> login(@Body LoginRequest request);

    @Headers({"Content-Type: application/json"})
    @POST("auth/register")
    Call<ResponseModel> register(@Body RegisterRequest registerRequest);

    // USER
    @GET("users/own")
    Call<ResponseModel<User>> getUser(@Header("Authorization") String token);

    // COURSE
    @Headers({"accept: */*"})
    @GET("courses/summary")
    Call<ResponseModel<List<Course>>> getCourses();
}
