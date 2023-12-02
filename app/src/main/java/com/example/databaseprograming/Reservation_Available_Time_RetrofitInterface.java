package com.example.databaseprograming;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Reservation_Available_Time_RetrofitInterface {
    //@통신 방식("통신 API명")
    @GET("/reservations/available-times")
    Call<Reservation_Available_Time_Response> getAvailableTime(@Query("hospitalId") String hospitalId, @Query("date") String date);
}
