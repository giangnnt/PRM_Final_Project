package com.example.prm392_final_project.Api;


import com.example.prm392_final_project.Model.Course;
import com.example.prm392_final_project.Model.PaymentResponse;
import com.example.prm392_final_project.Model.ResponseModel;
import com.example.prm392_final_project.Model.User;
import com.example.prm392_final_project.Model.Auth.LoginRequest;
import com.example.prm392_final_project.Model.Auth.RegisterRequest;
import com.example.prm392_final_project.Model.Auth.TokenData;

import java.util.List;
import java.util.UUID;

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

    @Headers({"accept: */*"})
    @GET("courses/{courseId}/summary")
    Call<ResponseModel<Course>> getCourseById(@Path("courseId") UUID courseId);

    //ORDER
    @Headers({"accept: */*"})
    @POST("orders/payment-url")
    Call<ResponseModel<PaymentResponse>> getPaymentResponse(
            @Header("Authorization") String token,
            @Query("courseId") UUID courseId);

}
