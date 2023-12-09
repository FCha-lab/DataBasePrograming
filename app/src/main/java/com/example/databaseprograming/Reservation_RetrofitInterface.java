package com.example.databaseprograming;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Reservation_RetrofitInterface {
    //@통신 방식("통신 API명")
    @POST("/reservations")
    Call<Reservation_Response> setReservation(@Body Reservation_Request request);


    //@통신 방식("통신 API명")
    @GET("/reservations/available-times")
    Call<Reservation_Available_Time_Response> getAvailableTime(@Query("hospitalId") String hospitalId, @Query("date") String date);


    //@통신 방식("통신 API명")
    @GET("/reservations/check")
    Call<ArrayList<Inquiry_of_Reservation_Information_Response>> getIOR();

    //병원 명칭으로 검색
    @GET("/reservations/search")
    Call<ArrayList<Inquiry_of_Reservation_Information_Response>> getSearchResultByName(@Query("hospitalName") String searchInfo);

    //예약 취소
    @DELETE("/reservations/cancel/{appointmentId}")
    Call<Reservation_Information_Delete_Response> deleteReservation(@Path("appointmentId") int id);
}
