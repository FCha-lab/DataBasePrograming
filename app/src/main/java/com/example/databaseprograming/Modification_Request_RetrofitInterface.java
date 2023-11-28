package com.example.databaseprograming;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;

public interface Modification_Request_RetrofitInterface {
    //@통신 방식("통신 API명")
    @PUT("/users/update")
    Call<UserInfo_Modification> setUserInfo(@Body UserInfo_Modification userInfo_modification);
}
