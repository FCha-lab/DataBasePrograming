package com.example.databaseprograming;

import com.google.gson.annotations.SerializedName;

public class Medical_Records_Detail_Response {
    //상세페이지 열람 시 서버에서 응답하는 병원 JSON 정보

    //진료기록 아이디
    @SerializedName("recordId")
    private int recordId;

    //병원 아이디
    @SerializedName("hospitalId")
    private String hospitalId;

    //진료기록 내용
    @SerializedName("content")
    private String content;

    //진료기록 날짜
    @SerializedName("recordDate")
    private String recordDate;

    //진료기록 환자 이름
    @SerializedName("patientName")
    private String patientName;

    //진료기록 환자 생일
    @SerializedName("birthDate")
    private String birthDate;

    //진료기록 병원 이름
    @SerializedName("hospitalName")
    private String hospitalName;

    //진료기록 병원 전화번호
    @SerializedName("phoneNumber")
    private String phoneNumber;

    //진료기록 의사 면허번호
    @SerializedName("licenseNumber")
    private String licenseNumber;

    //진료기록 의사 이름
    @SerializedName("doctorName")
    private String doctorName;

    //진료기록 좋아요 개수
    @SerializedName("countLikes")
    private int countLikes;

    //진료기록 좋아요 상태
    @SerializedName("likes")
    private boolean likes;

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

    public String getHospitalId() { return hospitalId; }
    public void setHospitalId(String hospitalId) { this.hospitalId = hospitalId; }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public int getCountLikes() {
        return countLikes;
    }

    public void setCountLikes(int countLikes) {
        this.countLikes = countLikes;
    }

    public boolean isLikes() {
        return likes;
    }

    public void setLikes(boolean likes) {
        this.likes = likes;
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
