package com.example.demoapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.demoapp.model.DomExport;
import com.example.demoapp.repository.DomExportRepository;

import retrofit2.Call;

public class DomExportViewModel extends AndroidViewModel {
    private DomExportRepository mDomExportRepository;

    public DomExportViewModel(@NonNull Application application) {
        super(application);
    }

    public Call<DomExport> insertData(String name, String weight, String quantity,
                                      String temp, String address, String portExport, String length,
                                      String height, String width, String type, String month, String continent){

        return mDomExportRepository.insertData(name, weight, quantity, temp, address, portExport, length, height, width, type, month, continent);
    }
}
