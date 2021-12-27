package com.example.demoapp.services;

import com.example.demoapp.model.Air;
import com.example.demoapp.model.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LogService {
    @FormUrlEncoded
    @POST("InsertLog.php")
    Call<Log> addData(@Field("tenhang") String tenhang, @Field("hscode") String hscode,
                      @Field("congdung") String congdung, @Field("hinhanh") String hinhanh,
                      @Field("cangdi") String cangdi, @Field("cangden") String cangden,
                      @Field("loaihang") String loaihang, @Field("soluongcuthe") String soluongcuthe,
                      @Field("yeucaudacbiet") String yeucaudacbiet, @Field("valid") String valid
                     , @Field("month") String month, @Field("importorexport") String importorexport) ;

    @GET("GetDataLog.php")
    Call<List<Log>> getpriceLog();
}
