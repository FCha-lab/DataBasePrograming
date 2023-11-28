package com.example.databaseprograming;

import com.google.gson.annotations.SerializedName;

public class Join_Response {
    //회원가입 시도 시 서버에서 응답하는 JSON 정보
    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private int status;

    @SerializedName("userName")
    private String userName;

    public String getMessage(){
        return message;
    }

    public int getStatus(){
        return status;
    }

    public String getUserName(){
        return userName;
    }


    @Override
    public String toString() {
        return "Join_Response{" +
                "message=" + message +
                ", status=" + status +
                '}';
    }

}
