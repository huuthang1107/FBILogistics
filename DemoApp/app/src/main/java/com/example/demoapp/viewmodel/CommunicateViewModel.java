package com.example.demoapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CommunicateViewModel extends ViewModel {
    private final MutableLiveData<Boolean> _needReloading = new MutableLiveData<>();
    private final LiveData<Boolean> needReloading = _needReloading;

    public CommunicateViewModel() {
        this._needReloading.postValue(false);
    }

    public LiveData<Boolean> needReloading() {
        return needReloading;
    }

    public void openDialog() {
        _needReloading.postValue(false);
    }

    public void makeChange() {
        _needReloading.postValue(true);
    }
}
