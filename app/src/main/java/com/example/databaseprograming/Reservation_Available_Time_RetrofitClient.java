package com.example.databaseprograming;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Reservation_Available_Time_RetrofitClient {
    private final String BASE_URL = "http://3.36.79.34:8080/reservations/available-times/";

    private Retrofit retrofit;

    public Reservation_Available_Time_RetrofitInterface getApiService() {
        return getInstance().create(Reservation_Available_Time_RetrofitInterface.class);
    } // api 콜

    private Retrofit getInstance() {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)) // 이 부분이 네트워크 로깅을 활성화하는 부분
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
