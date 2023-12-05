package com.example.databaseprograming;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Hospital_Info_Response {
    //상세페이지 열람 시 서버에서 응답하는 병원 JSON 정보
    //병원 아이디
    @SerializedName("hospitalId")
    private String hospitalId;

    //병원 이름
    @SerializedName("hospitalName")
    private String hospitalName;

    //병원 주소
    @SerializedName("address")
    private String address;

    //병원 진료과목
    @SerializedName("department")
    private String department;

    //병원 오픈날짜
    @SerializedName("openDate")
    private String openDate;

    //병원 전화번호
    @SerializedName("phoneNumber")
    private String phoneNumber;

    //병원 운영시간
    @SerializedName("operatingHours")
    private String operatingHours;

    //병원 휴게시간
    @SerializedName("breakTime")
    private String breakTime;

    //병원 운영 상태
    @SerializedName("hospitalStatus")
    private String hospitalStatus;

    //병원 좋아요 수
    @SerializedName("likesCount")
    private int likesCount;

    //병원 독타 목록
    @SerializedName("doctors")
    private ArrayList<Doctor_Info> doctors;

    //병원을 유저가 좋아요 했는지 안했는지
    @SerializedName("likedByUser")
    private boolean likedByUser;

    //메세지
    @SerializedName("message")
    private String message;

    // Getter and Setter methods
    public String getHospitalId() {
        return hospitalId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public String getAddress() {
        return address;
    }

    public String getDepartment() {
        return department;
    }

    public String getOpenDate() {
        return openDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getOperatingHours() {
        return operatingHours;
    }

    public String getHospitalStatus() {
        return hospitalStatus;
    }

    public String getBreakTime() {
        return breakTime;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public ArrayList<Doctor_Info> getDoctors() {
        return doctors;
    }

    public boolean isLikedByUser() {
        return likedByUser;
    }

    public String getMessage() {
        return message;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setOperatingHours(String operatingHours) {
        this.operatingHours = operatingHours;
    }

    public void setBreakTime(String breakTime) {
        this.breakTime = breakTime;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public void setDoctors(ArrayList<Doctor_Info> doctors) {
        this.doctors = doctors;
    }

    public void setLikedByUser(boolean likedByUser) {
        this.likedByUser = likedByUser;
    }

    // Nested class for DoctorInfo
    public static class Doctor_Info {
        @SerializedName("name")
        private String name;

        @SerializedName("biography")
        private String biography;

        @SerializedName("gender")
        private String gender;

        // Getter and Setter methods

        public String getDoctorName() {
            return name;
        }

        public String getBiography() {
            return biography;
        }

        public String getGender() {
            return gender;
        }

        public void setDoctorName(String name) {
            this.name = name;
        }

        public void setBiography(String biography) {
            this.biography = biography;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }
    }


}
