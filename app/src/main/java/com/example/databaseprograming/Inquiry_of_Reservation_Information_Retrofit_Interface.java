package com.example.databaseprograming;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Inquiry_of_Reservation_Information_Retrofit_Interface {
    //@통신 방식("통신 API명")
    @GET("/reservations/check")
    Call<ArrayList<Inquiry_of_Reservation_Information_Response>> getIOR();
}