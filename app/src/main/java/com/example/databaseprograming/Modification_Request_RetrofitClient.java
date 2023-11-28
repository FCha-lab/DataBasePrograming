package com.example.databaseprograming;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Modification_Request_RetrofitClient {
    private final String BASE_URL = "http://3.36.79.34:8080/users/update/";

    private Retrofit retrofit;

    public Modification_Request_RetrofitInterface getApiService(String token) {
        return getInstance(token).create(Modification_Request_RetrofitInterface.class);
    } // api 콜

    private Retrofit getInstance(String token) {

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request originalRequest = chain.request();

                    // 여기서 "Authorization" 헤더에 토큰을 추가합니다.
                    Request newRequest = originalRequest.newBuilder()
                            .header("Authorization", "Bearer " + token)
                            .build();

                    return chain.proceed(newRequest);
                })

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
