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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {
    private final ApiService apiService;

    public AuthRepository() {
        apiService = RetrofitClient.getApiService();
    }

    public LiveData<TokenData> loginUser(String email, String password) {
        MutableLiveData<TokenData> loginResult = new MutableLiveData<>();
        LoginRequest request = new LoginRequest(email, password);

        apiService.login(request).enqueue(new Callback<ResponseModel<TokenData>>() {
            @Override
            public void onResponse(Call<ResponseModel<TokenData>> call, Response<ResponseModel<TokenData>> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getResult() != null) {
                    loginResult.postValue(response.body().getResult().getData());
                } else {
                    loginResult.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<ResponseModel<TokenData>> call, Throwable t) {
                loginResult.postValue(null);
            }
        });

        return loginResult;
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