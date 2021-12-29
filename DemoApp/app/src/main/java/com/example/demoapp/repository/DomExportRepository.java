package com.example.demoapp.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.demoapp.model.DomExport;
import com.example.demoapp.services.DomExportService;
import com.example.demoapp.utilities.APIClient;

import java.util.List;

import retrofit2.Call;

public class DomExportRepository {
    private MutableLiveData<List<DomExport>> listExport;
    private final DomExportService mDomExportService;

    public DomExportRepository(String baseURL){
        mDomExportService = APIClient.getClient(baseURL).create(DomExportService.class);
    }

    public Call<DomExport> insertData( String name, String weight, String quantity,
                                      String temp, String address, String portExport, String length,
                                      String height, String width, String type, String month, String continent){

        return mDomExportService.insertData( name, weight, quantity, temp, address, portExport, length, height, width, type, month, continent);
    }
}
