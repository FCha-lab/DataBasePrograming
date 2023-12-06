package com.example.databaseprograming;

import com.google.gson.annotations.SerializedName;

public class Like_Info {
    //좋아요 정보에 대해 서버에서 응답하는 JSON 정보
    //메세지
    @SerializedName("message")
    private String message;

    //에러
    @SerializedName("error")
    private String error;

    //상태
    @SerializedName("status")
    private String status;

    //좋아요 수
    @SerializedName("total")
    private Integer total;

    //병원 아이디
    @SerializedName("hospitalId")
    private String hospitalId;

    public String getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }

    public String getStatus() {
        return status;
    }

    public Integer getTotal() {
        return total;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }
}
