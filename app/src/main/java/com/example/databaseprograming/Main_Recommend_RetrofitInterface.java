package com.example.databaseprograming;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Main_Recommend_RetrofitInterface {
    //@통신 방식("통신 API명")
    @GET("/hospitals/top")
    Call<ArrayList<Main_Recommend_Response>> getTopH();
}
