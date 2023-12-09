package com.example.databaseprograming;

import com.google.gson.annotations.SerializedName;

public class Medical_Records_Inquiry_Response {
    //상세페이지 열람 시 서버에서 응답하는 병원 JSON 정보

    //진료기록 아이디
    @SerializedName("recordId")
    private int recordId;

    //진료기록 날짜
    @SerializedName("recordDate")
    private String recordDate;

    //진료기록 의사이름
    @SerializedName("doctorName")
    private String doctorName;

    //진료기록 병원이름
    @SerializedName("hospitalName")
    private String hospitalName;

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("error")
    private String error;




    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
