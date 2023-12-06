package com.example.databaseprograming;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Logout_Response {
    //로그아웃 시 서버에서 응답하는 JSON 정보
    @SerializedName("message")
    private String message;

    @SerializedName("error")
    private String error;

    @SerializedName("status")
    private String status;

    public String getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }

    public String getStatus() {
        return status;
    }

    @NonNull
    @Override
    public String toString() {
        String s = "Logout Response{" +
                "error : " + getError() + ", " +
                "message : " + getMessage() + ", " +
                "status : " + getStatus();
        return s;
    }
}
