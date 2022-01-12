package com.example.demoapp.view.dialog.air;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.demoapp.R;
import com.example.demoapp.databinding.FragmentInsertAirImportDialogBinding;
import com.example.demoapp.model.AirExport;
import com.example.demoapp.model.AirImport;
import com.example.demoapp.utilities.Constants;
import com.example.demoapp.viewmodel.AirExportViewModel;
import com.example.demoapp.viewmodel.AirImportViewModel;
import com.example.demoapp.viewmodel.CommunicateViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class InsertAirImportDialog extends DialogFragment implements View.OnClickListener {
    private final String[] listStr = new String[2];
    private FragmentInsertAirImportDialogBinding mInsertAirImportDialogBinding;
    private ArrayAdapter<String> adapterItemsMonth, adapterItemsContinent;

    private AirImportViewModel mAirViewModel;
    private List<AirImport> airList = new ArrayList<>();
    private CommunicateViewModel mCommunicateViewModel;
    public static InsertAirImportDialog insertDiaLogAIRImport(){
        return new InsertAirImportDialog();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mInsertAirImportDialogBinding = FragmentInsertAirImportDialogBinding.inflate(inflater, container, false);
        View view = mInsertAirImportDialogBinding.getRoot();

        mAirViewModel = new ViewModelProvider(this).get(AirImportViewModel.class);
        mCommunicateViewModel = new ViewModelProvider(getActivity()).get(CommunicateViewModel.class);

        initView();

        return view;
    }

    private void initView() {
        adapterItemsMonth = new ArrayAdapter<String>(getContext(), R.layout.dropdown_item, Constants.ITEMS_MONTH);
        adapterItemsContinent = new ArrayAdapter<String>(getContext(), R.layout.dropdown_item, Constants.ITEMS_CONTINENT);

        mInsertAirImportDialogBinding.insertAutoMonth.setAdapter(adapterItemsMonth);
        mInsertAirImportDialogBinding.insertAutoContinent.setAdapter(adapterItemsContinent);

        mInsertAirImportDialogBinding.btnFunctionAddAirImport.setOnClickListener(this);
        mInsertAirImportDialogBinding.btnFunctionCancelAirImport.setOnClickListener(this);

        mInsertAirImportDialogBinding.insertAutoMonth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listStr[0] = adapterView.getItemAtPosition(i).toString();
            }
        });

        mInsertAirImportDialogBinding.insertAutoContinent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listStr[1] = adapterView.getItemAtPosition(i).toString();
            }
        });

        setCancelable(false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_function_add_air_import:
                insertAirImport();
                dismiss();
                break;
            case R.id.btn_function_cancel_air_import:
                dismiss();
                break;
        }
    }

    private void insertAirImport() {
        String stPol = mInsertAirImportDialogBinding.tfPolAirImport.getEditText().getText().toString();
        String stPod = mInsertAirImportDialogBinding.tfPodAirImport.getEditText().getText().toString();
        String stDim = mInsertAirImportDialogBinding.tfDimAirImport.getEditText().getText().toString();
        String stGross = mInsertAirImportDialogBinding.tfGrossAirImport.getEditText().getText().toString();
        String stType = mInsertAirImportDialogBinding.tfTypeofcargoAirImport.getEditText().getText().toString();
        String stFreight = mInsertAirImportDialogBinding.tfAirfreightAirImport.getEditText().getText().toString();
        String stSurcharge = mInsertAirImportDialogBinding.tfSurchargeAirImport.getEditText().getText().toString();
        String stLines = mInsertAirImportDialogBinding.tfAirlinesAirImport.getEditText().getText().toString();
        String stSchedule = mInsertAirImportDialogBinding.tfScheduleAirImport.getEditText().getText().toString();
        String stTransittime = mInsertAirImportDialogBinding.tfTfTransitTimeAirImport.getEditText().getText().toString();
        String stValid = mInsertAirImportDialogBinding.tfValidAirImport.getEditText().getText().toString();
        String stNote = mInsertAirImportDialogBinding.tfNotesAirImport.getEditText().getText().toString();


        mCommunicateViewModel.makeChanges();
        Call<AirImport> call = mAirViewModel.insertAir(stPol,stPod, stDim, stGross, stType, stFreight,
                stSurcharge, stLines, stSchedule, stTransittime, stValid, stNote, listStr[0], listStr[1]);
        call.enqueue(new Callback<AirImport>() {
            @Override
            public void onResponse(Call<AirImport> call, Response<AirImport> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getContext(), "Created Successful!!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AirImport> call, Throwable t) {

            }
        });
    }
}