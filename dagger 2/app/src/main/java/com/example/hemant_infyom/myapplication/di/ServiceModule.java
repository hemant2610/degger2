package com.example.hemant_infyom.myapplication.di;

/**
 * Created by Linux on 8/22/2016.
 */


import com.example.hemant_infyom.myapplication.service.ApiService;
import com.example.hemant_infyom.myapplication.service.RetrofitService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.Retrofit;

@Module
public class ServiceModule {
    @Provides
    @Singleton
    public RetrofitService providesRetrofitService() {
        return new RetrofitService();
    }

    @Provides
    @Singleton
    public ApiService providesApiService(RetrofitService retrofitService) {
        Retrofit retrofit = retrofitService.buildRedditRetrofit();
        return retrofit.create(ApiService.class);
    }
}
