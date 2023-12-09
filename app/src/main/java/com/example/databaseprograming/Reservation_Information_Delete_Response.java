package com.example.databaseprograming;

import com.google.gson.annotations.SerializedName;

public class Reservation_Information_Delete_Response {

    //아이디
    @SerializedName("appointmentId")
    private int appointmentId;

    //아이디
    @SerializedName("message")
    private String message;

    //아이디
    @SerializedName("status")
    private String status;

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
