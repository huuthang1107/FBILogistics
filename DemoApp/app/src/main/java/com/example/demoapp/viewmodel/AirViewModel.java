package com.example.demoapp.viewmodel;

import android.app.Application;
import android.provider.SyncStateContract;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.demoapp.model.Air;
import com.example.demoapp.model.Fcl;
import com.example.demoapp.repository.AirRepository;
import com.example.demoapp.repository.FclRepository;
import com.example.demoapp.utilities.Contants;

import java.util.List;

import retrofit2.Call;

public class AirViewModel extends AndroidViewModel {
    private LiveData<List<Air>> mAirList;
    private AirRepository mAirRepository;

    public AirViewModel(Application application) {
        super(application);

        init();

    }

    public void init() {
        mAirRepository = new AirRepository(Contants.URL_API);
    }

    public Call<Air> insertAir(String aol, String aod, String dim, String grossweight, String typeofcargo, String airfreight,
                               String surcharge, String airlines, String schedule, String transittime, String valid, String note
            , String month ,String continent) {
        return  mAirRepository.insertAir(aol, aod, dim, grossweight,typeofcargo, airfreight, surcharge, airlines,
                schedule, transittime, valid, note, month, continent);
    }

    public void loadAllAir() {
        mAirList = mAirRepository.getAllAir();
    }

    public LiveData<List<Air>> getLclList() {
        loadAllAir();
        return mAirList;
    }


}
