package com.example.demoapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.demoapp.model.Air;
import com.example.demoapp.model.Fcl;
import com.example.demoapp.services.AIRService;
import com.example.demoapp.services.FCLService;
import com.example.demoapp.utilities.APIClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AirRepository {

    private MutableLiveData<List<Air>> mAirList;
    private AIRService mAirService;


    public AirRepository(String baseURL) {
        mAirService = APIClient.getClient(baseURL).create(AIRService.class);
    }


    public void upLoadAllAir() {
        Call<List<Air>> call = mAirService.getpriceAIR();
        call.enqueue(new Callback<List<Air>>() {
            @Override
            public void onResponse(Call<List<Air>> call, Response<List<Air>> response) {
                if (response.body() != null) {
                    mAirList.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Air>> call, Throwable t) {

                mAirList.postValue(null);
            }
        });
    }

    /**
     * This method will store data which get from fcl table
     *
     * @return list of Fcl table
     */

    public LiveData<List<Air>> getAllAir() {
        if (mAirList == null) {
            mAirList = new MutableLiveData<>();
        }
        upLoadAllAir();
        return mAirList;
    }

    public Call<Air> insertAir(String aol, String aod, String dim, String grossweight, String typeofcargo, String airfreight,
                               String surcharge, String airlines, String schedule, String transittime, String valid, String note
                               , String month ,String continent) {
        return mAirService.addData(aol, aod, dim, grossweight,typeofcargo, airfreight, surcharge, airlines,
                schedule, transittime, valid, note, month, continent);
    }
}
