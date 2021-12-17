package com.example.demoapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.demoapp.model.Import;
import com.example.demoapp.services.ImportService;
import com.example.demoapp.utilities.APIClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImportRepository {
    private MutableLiveData<List<Import>> mImportList;
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
        Call<List<Import>> call = mImportService.getStatusImport();
        call.enqueue(new Callback<List<Import>>() {
            @Override
            public void onResponse(Call<List<Import>> call, Response<List<Import>> response) {
                if (response.body() != null) {
                    mImportList.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Import>> call, Throwable t) {
                mImportList.postValue(null);
            }
        });
    }

    /**
     * This method will store all data which get from Import Table
     * @return list of Import
     */

    public LiveData<List<Import>> getAllImport() {
        if (mImportList == null) {
            mImportList = new MutableLiveData<>();
        }
        upLoadAllImport();
        return mImportList;
    }
}
