package com.example.demoapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.demoapp.model.DetailsPojoFcl;
import com.example.demoapp.model.DetailsPojoImport;
import com.example.demoapp.model.Fcl;
import com.example.demoapp.model.Import;
import com.example.demoapp.services.FCLService;
import com.example.demoapp.services.ImportService;
import com.example.demoapp.utilities.APIClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImportRepository {
    private MutableLiveData<List<DetailsPojoImport>> mImportList;
    private ImportService mImportService;

    /**
     * This method used as constructor of Import Repository
     * @param baseURL Url of API
     */
    public ImportRepository(String baseURL) {
        mImportService = APIClient.getClient(baseURL).create(ImportService.class);
    }

    /**
     * This method will upload all data of Import table on database
     */
    public void upLoadAllImport() {
        Call<List<DetailsPojoImport>> call = mImportService.getStatusImport();
        call.enqueue(new Callback<List<DetailsPojoImport>>() {
            @Override
            public void onResponse(Call<List<DetailsPojoImport>> call, Response<List<DetailsPojoImport>> response) {
                if (response.body() != null) {
                    mImportList.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<DetailsPojoImport>> call, Throwable t) {
                mImportList.postValue(null);
            }
        });
    }

    /**
     * This method will store all data which get from Import Table
     * @return list of Import
     */

    public LiveData<List<DetailsPojoImport>> getAllImport() {
        if (mImportList == null) {
            mImportList = new MutableLiveData<>();
        }
        upLoadAllImport();
        return mImportList;
    }
}
