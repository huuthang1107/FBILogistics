package com.example.demoapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.demoapp.model.DetailsPojoFcl;
import com.example.demoapp.model.Fcl;
import com.example.demoapp.services.FCLService;
import com.example.demoapp.utilities.APIClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FclRepository {
    private MutableLiveData<List<DetailsPojoFcl>> mFclList;
    private FCLService mFclService;
    private MutableLiveData<Fcl> mFcl;
    private boolean mIsSuccess;

    public FclRepository(String baseURL) {
        mFclService = APIClient.getClient(baseURL).create(FCLService.class);
        mIsSuccess = false;
    }

    public void upLoadAllFcl() {
        Call<List<DetailsPojoFcl>> call = mFclService.getStatusFcl();
        call.enqueue(new Callback<List<DetailsPojoFcl>>() {
            @Override
            public void onResponse(Call<List<DetailsPojoFcl>> call, Response<List<DetailsPojoFcl>> response) {
                if (response.body() != null) {
                    mFclList.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<DetailsPojoFcl>> call, Throwable t) {
                mFclList.postValue(null);
            }
        });
    }

    public LiveData<List<DetailsPojoFcl>> getAllFcl() {
        if (mFclList == null) {
            mFclList = new MutableLiveData<>();
        }
        upLoadAllFcl();
        return mFclList;
    }
}
