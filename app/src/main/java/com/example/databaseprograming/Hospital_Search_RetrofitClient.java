package com.example.databaseprograming;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Hospital_Search_RetrofitClient {
    private static final String BASE_URL = "http://3.36.79.34:8080/hospitals/";

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)) // 이 부분이 네트워크 로깅을 활성화하는 부분
            .build();

    private static Retrofit retrofit;

    public Hospital_Search_RetrofitInterface getApiService(){
        return getInstance().create(Hospital_Search_RetrofitInterface.class);
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
