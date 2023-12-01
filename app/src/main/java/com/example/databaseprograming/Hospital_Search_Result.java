package com.example.databaseprograming;

import com.google.gson.annotations.SerializedName;

public class Hospital_Search_Result {
    //검색에 대한 결과 JSON 정보
    //병원 아이디
    @SerializedName("id")
    private String id;

    //병원 이름
    @SerializedName("name")
    private String name;

    //병원 주소
    @SerializedName("address")
    private String address;

    //병원 번호
    @SerializedName("phoneNumber")
    private String phoneNumber;

    //병원 진료과목
    @SerializedName("department")
    private String department;

    //병원 진료시간
    @SerializedName("operatingHours")
    private String operatingHours;

    //병원 좋아요 수
    @SerializedName("likeCount")
    private int likeCount;

    //병원 좋아요 수
    @SerializedName("message")
    private String message;

    //병원 좋아요 수
    @SerializedName("status")
    private int status;

    public void setId(String id) {
        this.id = id;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setName(String name) {
        this.name = name;    }

    public void setAddress(String address) {
        this.address = address;
    }
    public void setDepartment(String department) {
        this.department = department;
    }

    public void setOperatingHours(String operatingHours) {
        this.operatingHours = operatingHours;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public String getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getDepartment() {
        return department;
    }

    public String getOperatingHours() {
        return operatingHours;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
