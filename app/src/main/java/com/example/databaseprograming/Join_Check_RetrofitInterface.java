package com.example.databaseprograming;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Join_Check_RetrofitInterface {
    //@통신 방식("통신 API명")
    @GET("/users/duplication")
    Call<Join_Duplication> getDuplicationCheck(@Query("userId") String userId);
}
