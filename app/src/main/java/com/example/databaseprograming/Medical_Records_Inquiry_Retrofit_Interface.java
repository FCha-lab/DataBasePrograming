package com.example.databaseprograming;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Medical_Records_Inquiry_Retrofit_Interface {
    //@통신 방식("통신 API명")
    
    //진료기록 조회
    @GET("/records/check")
    Call<ArrayList<Medical_Records_Inquiry_Response>> getMRI();

    //진료기록 상세
    @GET("/records/details/{record_id}")
    Call<Medical_Records_Detail_Response> getMRD(@Path("record_id") int record_id);

    //병원 명칭으로 검색
    @GET("/records/search")
    Call<ArrayList<Medical_Records_Inquiry_Response>> getSearchResultByName(@Query("hospitalName") String searchInfo);

}
