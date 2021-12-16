package com.example.demoapp.api;

import com.example.demoapp.model.Air;
import com.example.demoapp.model.DetailsAIR;
import com.example.demoapp.model.DetailsPojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;

public interface GetAIR {
    String BASE_URL = "http://192.168.1.180/dataAIR/";

    @GET("GetDataAIR.php")
    Call<List<DetailsAIR>> getpricelistAIR();
}
