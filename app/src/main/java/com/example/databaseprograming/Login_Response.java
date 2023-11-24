package com.example.databaseprograming;

import com.google.gson.annotations.SerializedName;

public class Login_Response {
    //로그인 시도 시 서버에서 응답하는 JSON 정보
    @SerializedName("message")
    private String message;

    @SerializedName("userName")
    private String userName;

    @SerializedName("status")
    private int status;

    public String getMessage(){
        return message;
    }

    public int getStatus(){
        return status;
    }


    @Override
    public String toString() {
        return "Login_Response{" +
                "message=" + message +
                ", status=" + status +
                ", userName=" + userName +
                '}';
    }

}
