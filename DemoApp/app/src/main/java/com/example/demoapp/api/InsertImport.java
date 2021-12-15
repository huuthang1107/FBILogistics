package com.example.demoapp.api;

import com.example.demoapp.model.Fcl;
import com.example.demoapp.model.Import;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InsertImport {
    @FormUrlEncoded
    @POST("InsertImport.php")
    Call<Import> addData(@Field("pol") String pol,
                         @Field("pod") String pod, @Field("of20") String of20,
                         @Field("of40") String of40, @Field("surcharge") String surcharge,
                         @Field("total_freight") String totalFreight, @Field("carrier") String carrier,
                         @Field("schedule") String schedule, @Field("transit_time") String transitTime,
                         @Field("free_time") String freeTime, @Field("valid") String valid,
                         @Field("note") String note, @Field("type") String type, @Field("month") String month,
                         @Field("continent") String continent);
}
