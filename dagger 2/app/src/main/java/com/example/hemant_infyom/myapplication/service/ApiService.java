package com.example.hemant_infyom.myapplication.service;

/**
 * Created by Linux on 8/22/2016.
 */

import com.example.hemant_infyom.myapplication.model.Users;


import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;


public interface ApiService {

    @GET("/users")
    Call<List<Users>> getData();
}
