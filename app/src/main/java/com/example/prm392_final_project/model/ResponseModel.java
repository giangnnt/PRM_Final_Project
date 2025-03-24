package com.example.prm392_final_project.model;

import com.google.gson.annotations.SerializedName;

public class ResponseModel<T> {
    @SerializedName("statusCode")
    private int statusCode;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("result")
    private Result<T> result;

    public int getStatusCode() { return statusCode; }
    public String getMessage() { return message; }
    public boolean isSuccess() { return isSuccess; }
    public Result<T> getResult() { return result; }

    public static class Result<T> {
        @SerializedName("data")
        private T data;  // Generic Type for different response data

        @SerializedName("paginationResp")
        private PaginationResponse paginationResp;

        public T getData() { return data; }
        public PaginationResponse getPaginationResp() { return paginationResp; }
    }
}
