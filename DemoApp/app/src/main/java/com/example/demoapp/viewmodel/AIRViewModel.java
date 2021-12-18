package com.example.demoapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.demoapp.model.DetailsAIR;
import com.example.demoapp.model.DetailsPojoFcl;
import com.example.demoapp.repository.AirRepository;
import com.example.demoapp.repository.FclRepository;
import com.example.demoapp.utilities.Constants;

import java.util.List;

import retrofit2.Call;

public class AIRViewModel extends AndroidViewModel {
    private LiveData<List<DetailsAIR>> mAIRList;
    private AirRepository airRepository;


    public AIRViewModel(@NonNull Application application) {
        super(application);
        init();
    }

    public void init() {
        airRepository = new AirRepository(Constants.URL_API);
    }

    public void loadAllAIR() {
        mAIRList = airRepository.getAllAIR();
    }

    public LiveData<List<DetailsAIR>> getAIRList() {
        loadAllAIR();
        return mAIRList;
    }
    public Call<DetailsAIR> addData(String aol, String aod, String dim, String grossweight, String typeofcargo,
                                String airfreight, String surcharge, String airlines, String schedule,
                                String transittime, String valid, String note, String month, String continent){
        return airRepository.CreateData(aol,aod, dim, grossweight, typeofcargo, airfreight, surcharge, airlines,
                schedule, transittime, valid, note, month, continent);

    }
}
