package com.example.databaseprograming;

import com.google.gson.annotations.SerializedName;

public class Login_Request {
    //로그인 시도 시 입력 정보에 대한 JSON 정보
    //입력한 아이디
    @SerializedName("userId")
    public String userId;

    //입력한 비밀번호
    @SerializedName("password")
    public String password;

    public String getuserId() {
        return userId;
    }

    public String getpassword() {
        return password;
    }

    public void setuserId(String userId) {
        this.userId = userId;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public Login_Request(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
