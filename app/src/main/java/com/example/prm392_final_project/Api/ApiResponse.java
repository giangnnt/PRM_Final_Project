package com.example.prm392_final_project.Api;

import com.example.prm392_final_project.DataModels.Course;

import java.util.List;

public class ApiResponse {
    private boolean isSuccess;
    private String message;
    private CourseList result;

    public CourseList getResult() { return result; }
}

