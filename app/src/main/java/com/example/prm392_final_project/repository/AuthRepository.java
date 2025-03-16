package com.example.prm392_final_project.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.prm392_final_project.api.ApiService;
import com.example.prm392_final_project.api.RetrofitClient;
import com.example.prm392_final_project.model.ResponseModel;
import com.example.prm392_final_project.model.ResultWrapper;
import com.example.prm392_final_project.model.User;
import com.example.prm392_final_project.model.auth.LoginRequest;
import com.example.prm392_final_project.model.auth.RegisterRequest;
import com.example.prm392_final_project.model.auth.TokenData;
import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {
    private final ApiService apiService;

    public AuthRepository() {
        apiService = RetrofitClient.getApiService();
    }

    public LiveData<TokenData> login(String email, String password) {
        MutableLiveData<TokenData> loginData = new MutableLiveData<>();
        LoginRequest request = new LoginRequest(email, password);

        apiService.login(request).enqueue(new Callback<ResponseModel<TokenData>>() {
            @Override
            public void onResponse(Call<ResponseModel<TokenData>> call, Response<ResponseModel<TokenData>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ResponseModel<TokenData> responseBody = response.body();

                    if (responseBody.hashCode() == 200 && responseBody.getResult() != null) {
                        TokenData tokenData = responseBody.getResult().getData();
                        loginData.setValue(tokenData);
                        Log.d("LOGIN_SUCCESS", "Access Token: " + tokenData.getAccessToken());
                    } else {
                        Log.e("LOGIN_ERROR", "API Response is unsuccessful: " + responseBody.getResult());
                        loginData.setValue(null);
                    }
                } else {
                    Log.e("LOGIN_ERROR", "API Request failed: " + response.code());
                    loginData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<TokenData>> call, Throwable t) {
                Log.e("LOGIN_ERROR", "API Call Failed: " + t.getMessage());
                loginData.setValue(null);
            }
        });

        return loginData;
    }


    public LiveData<Boolean> register(String fullName, String email, String password, String phoneNumber, String avatarUrl) {
        MutableLiveData<Boolean> registerData = new MutableLiveData<>();
        RegisterRequest request = new RegisterRequest(fullName, email, password, phoneNumber, avatarUrl);

        apiService.register(request).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                registerData.setValue(response.isSuccessful());
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                registerData.setValue(false);
            }
        });
        return registerData;
    }
}