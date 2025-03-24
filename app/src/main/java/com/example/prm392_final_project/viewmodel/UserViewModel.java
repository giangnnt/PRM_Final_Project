package com.example.prm392_final_project.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.prm392_final_project.model.ResponseModel;
import com.example.prm392_final_project.model.User;
import com.example.prm392_final_project.repository.UserRepository;

public class UserViewModel extends ViewModel {
    private final UserRepository userRepository;

    public UserViewModel() {
        userRepository = new UserRepository();
    }

    public LiveData<User> getUser(String token) {
        return userRepository.getUser(token);
    }
}

