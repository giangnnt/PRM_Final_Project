package com.example.prm392_final_project.model.auth;

public class RegisterRequest {
    private String email;
    private String password;
    private String fullName;
    private String phoneNumber;
    private String avatarUrl;

    public RegisterRequest(String fullName, String email, String password, String phoneNumber, String avatarUrl) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.avatarUrl = avatarUrl;
    }
}
