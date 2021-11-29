package com.example.demoapp.table_function;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetAPI {
    String BASE_URL = "http://192.168.1.6/database/";

    @GET("GetData.php")
    Call<List<DetailsPojo>> getStatus();
}
