package com.example.demoapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.demoapp.model.Fcl;
import com.example.demoapp.services.FCLService;
import com.example.demoapp.utilities.APIClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FclRepository {
    private MutableLiveData<List<Fcl>> mFclList;
    private FCLService mFclService;

    /**
     * This method used as constructor for FCL Repository
     *
     * @param baseURL Url to API
     */
    public FclRepository(String baseURL) {
        mFclService = APIClient.getClient(baseURL).create(FCLService.class);
    }

    /**
     * This method will upload all data of fcl table on database
     */
    public void upLoadAllFcl() {
        Call<List<Fcl>> call = mFclService.getStatusFcl();
        call.enqueue(new Callback<List<Fcl>>() {
            @Override
            public void onResponse(Call<List<Fcl>> call, Response<List<Fcl>> response) {
                if (response.body() != null) {
                    mFclList.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Fcl>> call, Throwable t) {
                mFclList.postValue(null);
            }
        });
    }

    /**
     * This method will store data which get from fcl table
     *
     * @return list of Fcl table
     */

    public LiveData<List<Fcl>> getAllFcl() {
        if (mFclList == null) {
            mFclList = new MutableLiveData<>();
        }
        upLoadAllFcl();
        return mFclList;
    }

    public Call<Fcl> insertFcl(String pol, String pod, String of20, String of40, String su20, String su40,
                               String line, String notes, String valid, String notes2, String month, String type,
                               String continent) {
        return mFclService.addData(pol, pod, of20, of40, su20, su40, line, notes, valid, notes2, month, type, continent);
    }
}
