package com.example.databaseprograming;

import com.google.gson.annotations.SerializedName;

public class Inquiry_of_Reservation_Information_Response {
    //상세페이지 열람 시 서버에서 응답하는 병원 JSON 정보

    //아이디
    @SerializedName("id")
    private int id;

    //진료 날짜
    @SerializedName("date")
    private String date;

    //진료 시간
    @SerializedName("time")
    private String time;

    //진료 상태
    @SerializedName("status")
    private String status;

    //병원 아이디
    @SerializedName("hospitalId")
    private String hospitalId;

    //병원 이름
    @SerializedName("hospitalName")
    private String hospitalName;

    //병원 주소
    @SerializedName("hospitalAddress")
    private String hospitalAddress;

    //사용자 이름
    @SerializedName("userName")
    private String userName;

    @SerializedName("error")
    private String error;

    @SerializedName("message")
    private String message;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHospitalAddress() {
        return hospitalAddress;
    }

    public void setHospitalAddress(String hospitalAddress) {
        this.hospitalAddress = hospitalAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
