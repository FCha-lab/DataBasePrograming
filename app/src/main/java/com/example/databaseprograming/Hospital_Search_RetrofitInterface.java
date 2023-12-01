package com.example.databaseprograming;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Hospital_Search_RetrofitInterface {
    //@통신 방식("통신 API명")
    @GET("/hospitals")
    Call<ArrayList<Hospital_Search_Result>> getSearchResult(@Query("query") String searchInfo);
}
