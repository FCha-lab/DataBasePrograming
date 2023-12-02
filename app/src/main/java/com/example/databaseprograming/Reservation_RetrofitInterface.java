package com.example.databaseprograming;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface Reservation_RetrofitInterface {
    //@통신 방식("통신 API명")
    @POST("/reservations")
    Call<Reservation_Response> setReservation(@Body Reservation_Request request);
}
