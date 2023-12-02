package com.example.databaseprograming;

import com.google.gson.annotations.SerializedName;

public class Reservation_Request {
    //예약시도 시 서버에 보낼 예약 JSON 정보

    //예약 날짜
    @SerializedName("date")
    private String date;

    //예약 시간
    @SerializedName("time")
    private String time;

    //예약 병원 아이디
    @SerializedName("hospitalId")
    private String hospitalId;

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }
}
