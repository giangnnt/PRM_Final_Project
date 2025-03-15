package com.example.prm392_final_project.model;

public class ResponseModel<T> {
    private int statusCode;
    private String message;
    private boolean isSuccess;
    private ResultWrapper<T> result;  // ✅ Fix here: Use ResultWrapper<T>

    public ResultWrapper<T> getResult() {
        return result;
    }
}
