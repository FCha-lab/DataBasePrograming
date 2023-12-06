package com.example.databaseprograming;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface Users_RetrofitInterface {
    //@통신 방식("통신 API명")

    //회원정보 수정
    @PUT("/users/update")
    Call<UserInfo_Modification> setUserInfo(@Body UserInfo_Modification userInfo_modification);

    //회원 정보 조회
    @GET("/users/me")
    Call<UserInfo_Response> getUserInfoResponse();

    //로그인
    @POST("/users/login")
    Call<Login_Response> getLoginResponse(@Body Login_Request request);

    //회원 가입
    @POST("/users/save")
    Call<Join_Response> getJoinResponse(@Body Join_Request request);

    //아이디 중복 확인
    @GET("/users/duplication")
    Call<Join_Duplication> getDuplicationCheck(@Query("userId") String userId);

    //로그아웃
    @POST("/users/logout")
    Call<Logout_Response> callLogout();



}
