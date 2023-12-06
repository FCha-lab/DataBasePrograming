package com.example.databaseprograming;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Hospital_RetrofitInterface {
    //@통신 방식("통신 API명")
    //병원 상세 정보
    @GET("/hospitals/details/{id}")
    Call<Hospital_Info_Response> getHospitalInfo(@Path("id") String userId);

    //병원 명칭으로 검색
    @GET("/hospitals")
    Call<ArrayList<Hospital_Search_Result>> getSearchResultByName(@Query("query") String searchInfo);

    //병원 진료과목으로 검색
    @GET("/hospitals")
    Call<ArrayList<Hospital_Search_Result>> getSearchResultByDepartment(@Query("department") String searchInfo);

    //상위 3개 병원 배너
    @GET("/hospitals/top")
    Call<ArrayList<Main_Recommend_Response>> getTopH();


}
