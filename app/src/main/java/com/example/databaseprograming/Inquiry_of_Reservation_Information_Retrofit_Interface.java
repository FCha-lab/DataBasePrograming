package com.example.databaseprograming;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Inquiry_of_Reservation_Information_Retrofit_Interface {
    //@통신 방식("통신 API명")
    @GET("/reservations/check")
    Call<ArrayList<Inquiry_of_Reservation_Information_Response>> getIOR();

    //병원 명칭으로 검색
    @GET("/reservations/search")
    Call<ArrayList<Inquiry_of_Reservation_Information_Response>> getSearchResultByName(@Query("hospitalName") String searchInfo);

    //예약 취소
    @DELETE("/reservations/cancel/{info}")
    Call<ArrayList<Inquiry_of_Reservation_Information_Response>> deleteReservation(@Path("info") String info);
}