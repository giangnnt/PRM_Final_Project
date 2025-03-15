package com.example.prm392_final_project.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.prm392_final_project.model.ResponseModel;
import com.example.prm392_final_project.model.auth.TokenData;
import com.example.prm392_final_project.repository.AuthRepository;

public class AuthViewModel extends ViewModel {
    private final AuthRepository authRepository;

    public AuthViewModel() {
        authRepository = new AuthRepository();
    }

    public LiveData<TokenData> login(String email, String password) {
        return authRepository.login(email, password);
    }

    public LiveData<Boolean> register(String fullName, String email, String password, String phoneNumber, String avatarUrl) {
        return authRepository.register(fullName, email, password, phoneNumber, avatarUrl);
    }
}