package com.example.databaseprograming;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Medical_Records_Inquiry_Retrofit_Interface {
    //@통신 방식("통신 API명")
    @GET("/records/search")
    Call<ArrayList<Medical_Records_Inquiry_Response>> getMRI();
}
