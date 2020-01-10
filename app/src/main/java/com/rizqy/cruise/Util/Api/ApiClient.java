package com.rizqy.cruise.Util.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "http://172.16.10.11/cruise/public/api/";

    private static Retrofit mRetrofit;
    public static Retrofit getRetrofit(){
        if (mRetrofit == null){
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory( GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }
}
