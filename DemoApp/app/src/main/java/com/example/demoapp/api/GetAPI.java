package com.example.demoapp.api;

import com.example.demoapp.model.DetailsPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetAPI {
    String BASE_URL = "http://192.168.1.180/database/";

    @GET("GetData.php")
    Call<List<DetailsPojo>> getStatus();
}
