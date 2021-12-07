package com.example.demoapp.db;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetAPI {
    String BASE_URL = "http://192.168.1.3/database/";

    @GET("GetData.php")
    Call<List<DetailsPojo>> getStatus();
}
