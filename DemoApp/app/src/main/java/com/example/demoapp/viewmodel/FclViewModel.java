package com.example.demoapp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.demoapp.model.DetailsPojoFcl;
import com.example.demoapp.repository.FclRepository;
import com.example.demoapp.utilities.Constants;

import java.util.List;

public class FclViewModel extends AndroidViewModel {
    private LiveData<List<DetailsPojoFcl>> mFclList;
    private FclRepository mFclRepository;

    public FclViewModel(Application application) {
        super(application);

        init();

    }

    public void init() {
        mFclRepository = new FclRepository(Constants.URL_API);
    }

    public void loadAllFcl() {
        mFclList = mFclRepository.getAllFcl();
    }

    public LiveData<List<DetailsPojoFcl>> getFclList() {
        loadAllFcl();
        return mFclList;
    }

}
