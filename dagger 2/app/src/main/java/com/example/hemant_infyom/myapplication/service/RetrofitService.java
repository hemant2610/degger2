package com.example.hemant_infyom.myapplication.service;

/**
 * Created by Linux on 8/22/2016.
 */
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class RetrofitService {

    public Retrofit buildRedditRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
