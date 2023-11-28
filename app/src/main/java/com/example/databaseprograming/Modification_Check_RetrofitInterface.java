package com.example.databaseprograming;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Modification_Check_RetrofitInterface {
    //@통신 방식("통신 API명")
    @GET("/users/me")
    Call<UserInfo_Response> getUserInfoResponse();
}
