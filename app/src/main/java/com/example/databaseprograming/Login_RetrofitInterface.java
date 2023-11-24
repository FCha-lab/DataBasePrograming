package com.example.databaseprograming;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Login_RetrofitInterface {
    //@통신 방식("통신 API명")
    @POST("/users/login")
    Call<Login_Response> getLoginResponse(@Body Login_Request request);
}
