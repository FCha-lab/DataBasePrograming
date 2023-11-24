package com.example.databaseprograming;

import com.google.gson.annotations.SerializedName;

public class Login_Request {

    @SerializedName("userId")
    public String userId;

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
