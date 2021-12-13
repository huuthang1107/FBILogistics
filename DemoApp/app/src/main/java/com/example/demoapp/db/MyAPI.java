package com.example.demoapp.db;


import com.example.demoapp.model.PriceListAIR;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface MyAPI {
    @FormUrlEncoded
    @POST("InsertData.php")
    Call<PriceListModel> addData(@Field("pol") String pol,
                                 @Field("pod") String pod, @Field("of20") String of20,
                                 @Field("of40") String of40, @Field("su20") String su20,
                                 @Field("su40") String su40, @Field("linelist") String linelist,
                                 @Field("notes") String notes, @Field("valid") String valid,
                                 @Field("notes2") String notes2, @Field("month") String month,
                                 @Field("type") String type);

     @POST("InsertDataAIR.php")
    Call<PriceListAIR> addAIR(@Field("aol") String aol, @Field("aod") String aod,
                              @Field("dim") String dim, @Field("grossweight") String grossweight,
                              @Field("typeofcargo") String typeofcargo,
                              @Field("airfreight") String airfreight,
                              @Field("surcharge") String surcharge,
                              @Field("airlines") String airlines,
                              @Field("schedule") String schedule,
                              @Field("transittime") String transittime, @Field("valid") String valid,
                              @Field("note") String note, @Field("month") String month);

}
