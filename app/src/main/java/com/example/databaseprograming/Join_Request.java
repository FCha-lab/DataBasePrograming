package com.example.databaseprograming;

import com.google.gson.annotations.SerializedName;

public class Join_Request {
    //회원가입 시도 시 서버에서 응답하는 JSON 정보
    //유저아이디
    @SerializedName("userId")
    private String userId;

    //유저비밀번호
    @SerializedName("password")
    private String password;

    //유저의 이름
    @SerializedName("userName")
    private String userName;

    //유저의 생일
    @SerializedName("birthDate")
    private String birthDate;

    //유저전화번호
    @SerializedName("phoneNumber")
    private String phoneNumber;


    public void setuserId(String userId) {
        this.userId = userId;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public void setuserName(String userName) {
        this.userName = userName;
    }

    public void setbirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setphoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Join_Request() {

    }


}
