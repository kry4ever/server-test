package com.example.hasee_pc.localtest;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServerApi {

    @GET("/webhello/second")
    Call<User> getUser();
}
