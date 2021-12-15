package com.example.demoapp.api;

import com.example.demoapp.model.DetailsPojoFcl;
import com.example.demoapp.model.DetailsPojoImport;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetAPI {
    String BASE_URL = "http://192.168.1.199/database/";

    @GET("GetData.php")
    Call<List<DetailsPojoFcl>> getStatusFcl();

    @GET("GetDataImport.php")
    Call<List<DetailsPojoImport>> getStatusImport();
}
