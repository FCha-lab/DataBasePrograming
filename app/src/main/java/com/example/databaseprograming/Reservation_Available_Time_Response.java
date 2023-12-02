package com.example.databaseprograming;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Map;

public class Reservation_Available_Time_Response {
    //예약페이지 열람 시 서버에서 응답하는 병원 JSON 정보

    //병원 이름
    @SerializedName("hospitalName")
    private String hospitalName;


    //병원 가능 시간 목록
    @SerializedName("availableTime")
    private  ArrayList<Reservation_Available_Time_Response.Available_Time_List> availableTime;

    public class Available_Time_List {

        //시간
        @SerializedName("time")
        private String time;

        //남은 슬롯 수
        @SerializedName("availableSlots")
        private int availableSlots;

        public String getTime() {
            return time;
        }

        public int getAvailableSlots() {
            return availableSlots;
        }
    }

    //병원 이름
    @SerializedName("message")
    private String message;

    //병원 이름
    @SerializedName("status")
    private String status;

    public String getHospitalName() {
        return hospitalName;
    }

    public ArrayList<Available_Time_List> getAvailableTime() {
        return availableTime;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }
}
