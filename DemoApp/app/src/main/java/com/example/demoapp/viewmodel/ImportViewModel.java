package com.example.demoapp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.demoapp.model.DetailsPojoFcl;
import com.example.demoapp.model.DetailsPojoImport;
import com.example.demoapp.repository.FclRepository;
import com.example.demoapp.repository.ImportRepository;
import com.example.demoapp.utilities.Constants;

import java.util.List;

public class ImportViewModel extends AndroidViewModel {
    private LiveData<List<DetailsPojoImport>> mImportList;
    private ImportRepository mImportRepository;

    public ImportViewModel(Application application) {
        super(application);
        init();

    }

    public void init() {
        mImportRepository = new ImportRepository(Constants.URL_API);
    }

    public void loadAllImport() {
        mImportList = mImportRepository.getAllImport();
    }

    public LiveData<List<DetailsPojoImport>> getImportList() {
        loadAllImport();
        return mImportList;
    }
}
