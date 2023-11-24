package com.example.databaseprograming;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login_RetrofitClient {
    private static final String BASE_URL = "http://3.36.79.34:8080/users/login/";

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
//            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)) // 이 부분이 네트워크 로깅을 활성화하는 부분
            .build();

    private static Retrofit retrofit;

    public static Login_RetrofitInterface getApiService(){
        return getInstance().create(Login_RetrofitInterface.class);
    } // api 콜

    private static Retrofit getInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
