package com.example.prm392_final_project.viewmodel;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.prm392_final_project.model.ResponseModel;
import com.example.prm392_final_project.model.auth.LoginRequest;
import com.example.prm392_final_project.model.auth.TokenData;
import com.example.prm392_final_project.repository.AuthRepository;

public class AuthViewModel extends ViewModel {
    private final AuthRepository authRepository;
    private final MutableLiveData<TokenData> loginResult = new MutableLiveData<>();

    public AuthViewModel() {
        authRepository = new AuthRepository();
    }

    public LiveData<TokenData> getLoginResult() {
        return loginResult;
    }



    public LiveData<Boolean> register(String fullName, String email, String password, String phoneNumber, String avatarUrl) {
        return authRepository.register(fullName, email, password, phoneNumber, avatarUrl);
    }
}