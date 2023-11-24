package com.example.databaseprograming;

import com.google.gson.annotations.SerializedName;

public class UserInfo {
    @SerializedName("phoneNumber")
    private String phoneNumber;

    @SerializedName("message")
    private String message;

    @SerializedName("userName")
    private String userName;

    @SerializedName("userId")
    private String userId;

    @SerializedName("brithDate")
    private String brithDate;

    @SerializedName("status")
    private int status;
    //+ getter, setter 등등..
}