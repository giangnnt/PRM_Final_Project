package com.example.prm392_final_project.model.auth;
import com.google.gson.annotations.SerializedName;

public class TokenData {

    @SerializedName("accessToken")
    private String accessToken;

    @SerializedName("accessTokenExp")
    private long accessTokenExp;

    @SerializedName("refreshToken")
    private String refreshToken;

    @SerializedName("refreshTokenExp")
    private long refreshTokenExp;

    public String getAccessToken() { return accessToken; }
    public long getAccessTokenExp() { return accessTokenExp; }
    public String getRefreshToken() { return refreshToken; }
    public long getRefreshTokenExp() { return refreshTokenExp; }
}
