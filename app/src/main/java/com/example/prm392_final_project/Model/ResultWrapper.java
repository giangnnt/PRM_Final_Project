package com.example.prm392_final_project.Model;

import com.google.gson.annotations.SerializedName;

public class ResultWrapper<T> {
    @SerializedName("data")
    private T data; // ✅ Holds TokenData, User

    @SerializedName("paginationResp")
    private Object paginationResp; // Keep it flexible

    public T getData() {
        return data;
    }

    public Object getPaginationResp() {
        return paginationResp;
    }
}

