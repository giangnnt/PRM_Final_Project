package com.example.prm392_final_project.Model;

public class User {
    private String id;
    private int roleId;
    private String name;
    private String email;
    private String avatarUrl;
    private boolean isActive;

    public String getId() {
        return id;
    }

    public int getRoleId() {
        return roleId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    // Return default avatar if avatarUrl is null or empty
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public boolean isActive() {
        return isActive;
    }
}
