package com.example.databaseprograming;

import com.google.gson.annotations.SerializedName;

public class Join_Duplication {
    //회원가입 시 중복검사에 대해 서버에서 응답하는 JSON 정보
    @SerializedName("isAvailable")
    private Boolean isAvailable;

    @SerializedName("status")
    private int status;

    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public int getStatus() {
        return status;
    }
}
