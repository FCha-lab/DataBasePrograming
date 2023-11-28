package com.example.databaseprograming;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Join_RetrofitClient {
    private static final String BASE_URL = "http://3.36.79.34:8080/users/save/";

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
//            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)) // 이 부분이 네트워크 로깅을 활성화하는 부분
            .build();

    private static Retrofit retrofit;

    public Join_RetrofitInterface getApiService() {
        return getInstance().create(Join_RetrofitInterface.class);
    } // api 콜

    private Retrofit getInstance() {
        okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)) // 이 부분이 네트워크 로깅을 활성화하는 부분
                .build();
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
