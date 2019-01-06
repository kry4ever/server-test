package com.example.hasee_pc.localtest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ServerApi {

    @GET("/webhello/login")
    Call<String> getUser(@Query("name") String name, @Query("password") String password);
}
