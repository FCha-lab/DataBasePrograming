package com.example.databaseprograming;

import com.google.gson.annotations.SerializedName;

public class UserInfo_Modification {
    //회원정보수정에서 수정 시도 시 서버에 넘겨주는 JSON 정보

    //유저의 비밀번호
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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Modification_Request_Response{" +
                "password=" + password +
                ", phoneNumber=" + phoneNumber +
                ", birthDate=" + birthDate +
                ", userName=" + userName +
                '}';
    }
}
