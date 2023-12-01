package com.example.databaseprograming;

import com.google.gson.annotations.SerializedName;

public class Main_Recommend_Response {
    //메인화면에서 Top3에 대한 JSON 정보
    //병원 아이디
    @SerializedName("id")
    private String id;

    //병원 이름
    @SerializedName("name")
    private String name;

    //병원 주소
    @SerializedName("address")
    private String address;

    //병원 진료과목
    @SerializedName("department")
    private String department;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private int status;

    public String getAddress() {
        return address;
    }

    public String getDepartment() {
        return department;
    }

    public String getHName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Main_TopH_Response{" +
                "id=" + id +
                ", name=" + name +
                ", address=" + address +
                ", departments=" + department +
                '}';
    }

}
