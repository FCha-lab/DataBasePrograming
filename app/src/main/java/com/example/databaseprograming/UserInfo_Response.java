package com.example.databaseprograming;

import com.google.gson.annotations.SerializedName;

public class UserInfo_Response {
    //메인화면에서 회원정보 수정 시도 시 서버에서 응답하는 JSON 정보
    //유저아이디
    @SerializedName("userId")
    private String userId;

    //유저의 이름
    @SerializedName("userName")
    private String userName;

    //유저의 생일
    @SerializedName("birthDate")
    private String birthDate;

    //유저전화번호
    @SerializedName("phoneNumber")
    private String phoneNumber;

    //통신 상태 메세지
    @SerializedName("message")
    private String message;

    //통신 상태 번호
    @SerializedName("status")
    private String status;

    //통신 상태 번호
    @SerializedName("error")
    private String error;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "Modification_Check_Response{" +
                "message=" + message +
                ", status=" + status +
                '}';
    }
}
