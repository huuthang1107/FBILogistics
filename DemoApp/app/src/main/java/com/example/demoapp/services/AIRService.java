package com.example.demoapp.services;

import com.example.demoapp.model.Air;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AIRService {
    @FormUrlEncoded
    @POST("InsertAIR.php")
    Call<Air> addData(@Field("aol") String aol,
                      @Field("aod") String aod, @Field("dim") String dim,
                      @Field("grossweight") String grossweight, @Field("typeofcargo") String typeofcargo,
                      @Field("airfreight") String airfreight, @Field("surcharge") String surcharge,
                      @Field("airlines") String airlines, @Field("schedule") String schedule,
                      @Field("transittime") String transittime, @Field("valid") String valid,
                      @Field("note") String note, @Field("month") String month,
                      @Field("continent") String continent) ;

    @GET("GetDataAIR.php")
    Call<List<Air>> getpriceAIR();
}
