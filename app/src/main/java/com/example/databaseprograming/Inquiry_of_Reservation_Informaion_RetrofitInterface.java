package com.example.databaseprograming;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Inquiry_of_Reservation_Informaion_RetrofitInterface {
    //@통신 방식("통신 API명")
    @GET("/reservations/check")
    Call<ArrayList<Inquiry_of_Reservation_Informaion_Response>> getIOR();
}
