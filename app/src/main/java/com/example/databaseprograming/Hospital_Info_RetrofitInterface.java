package com.example.databaseprograming;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Hospital_Info_RetrofitInterface {
    //@통신 방식("통신 API명")
    @GET("/hospitals/details/{id}")
    Call<Hospital_Info_Response> getHospitalInfo(@Path("id") String userId);
}
