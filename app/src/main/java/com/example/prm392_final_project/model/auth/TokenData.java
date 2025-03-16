package com.example.prm392_final_project.model.auth;
import com.google.gson.annotations.SerializedName;

public class TokenData {

    private String accessToken;


    private String refreshToken;


    private long accessTokenExp;

    private long refreshTokenExp;

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public long getAccessTokenExp() {
        return accessTokenExp;
    }

    public long getRefreshTokenExp() {
        return refreshTokenExp;
    }
}
