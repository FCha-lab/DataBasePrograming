package com.example.databaseprograming;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Join_RetrofitInterface {
    //@통신 방식("통신 API명")
    @POST("/users/save")
    Call<Join_Response> getJoinResponse(@Body Join_Request request);
}
