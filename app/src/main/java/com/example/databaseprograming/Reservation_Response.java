package com.example.databaseprograming;

import com.google.gson.annotations.SerializedName;

public class Reservation_Response {
    //예약시도 시 서버에서 응답하는 예약 JSON 정보

    //예약 날짜
    @SerializedName("date")
    private String date;

    //예약 병원 아이디
    @SerializedName("hospitalId")
    private String hospitalId;

    //예약 시간
    @SerializedName("time")
    private String time;

    //예약자 이름
    @SerializedName("userName")
    private String userName;

    //통신 메세지
    @SerializedName("message")
    private String message;

    //통신 상태
    @SerializedName("status")
    private String status;

    public String getDate() {
        return date;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public String getTime() {
        return time;
    }

    public String getUserName() {
        return userName;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}
