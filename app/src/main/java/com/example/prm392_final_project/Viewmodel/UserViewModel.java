package com.example.prm392_final_project.Viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.prm392_final_project.Model.User;
import com.example.prm392_final_project.Repository.UserRepository;

public class UserViewModel extends ViewModel {
    private final UserRepository userRepository;

    public UserViewModel() {
        userRepository = new UserRepository();
    }

    public LiveData<User> getUser(String token) {
        return userRepository.getUser(token);
    }
}

