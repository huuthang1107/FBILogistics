package com.example.demoapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.demoapp.model.DetailsAIR;
import com.example.demoapp.model.DetailsPojoFcl;
import com.example.demoapp.services.AIRService;
import com.example.demoapp.services.FCLService;
import com.example.demoapp.utilities.APIClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AirRepository {
    private MutableLiveData<List<DetailsAIR>> mAirList;
    private AIRService mAIRService;

    /**
     * This method used as constructor for AIR Repository
     * @param baseURL Url to API
     */
    public AirRepository(String baseURL) {
        mAIRService = APIClient.getClient(baseURL).create(AIRService.class);
    }

    /**
     * This method will upload all data of air table on database
     */
    public void upLoadAllAIR() {
        Call<List<DetailsAIR>> call = mAIRService.getpriceAIR();

        call.enqueue(new Callback<List<DetailsAIR>>() {
            @Override
            public void onResponse(Call<List<DetailsAIR>> call, Response<List<DetailsAIR>> response) {
                if (response.body() != null) {
                    mAirList.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<DetailsAIR>> call, Throwable t) {
                mAirList.postValue(null);
            }
        });
    }

    /**
     * This method will store data which get from air table
     * @return list of Fcl table
     */

    public LiveData<List<DetailsAIR>> getAllAIR() {
        if (mAirList == null) {
            mAirList = new MutableLiveData<>();
        }
        upLoadAllAIR();
        return mAirList;
    }

    public Call<DetailsAIR> CreateData(String aol, String aod, String dim, String grossweight, String typeofcargo,
                                    String airfreight, String surcharge, String airlines, String schedule,
                                    String transittime, String valid, String note, String month, String continent){
        return mAIRService.addData(aol,aod, dim, grossweight, typeofcargo, airfreight, surcharge, airlines,
                schedule, transittime, valid, note, month, continent);
    }

}

