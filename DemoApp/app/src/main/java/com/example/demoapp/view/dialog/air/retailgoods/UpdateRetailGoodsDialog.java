package com.example.demoapp.view.dialog.air.retailgoods;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.demoapp.R;
import com.example.demoapp.databinding.FragmentUpdateRetailGoodsDialogBinding;
import com.example.demoapp.model.RetailGoods;
import com.example.demoapp.utilities.Constants;
import com.example.demoapp.viewmodel.CommunicateViewModel;
import com.example.demoapp.viewmodel.RetailGoodsViewModel;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UpdateRetailGoodsDialog extends DialogFragment implements View.OnClickListener {
    private FragmentUpdateRetailGoodsDialogBinding mRetailGoodsDialogBinding;
    private final String[] listPriceRetailGoods = new String[2];
    private RetailGoodsViewModel mRetailGoodsViewModel;
    private Bundle mBundle;
    private RetailGoods mRetailGoods;
    private CommunicateViewModel mCommunicateViewModel;


    public static UpdateRetailGoodsDialog getInstance(){
        return new UpdateRetailGoodsDialog();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRetailGoodsDialogBinding = FragmentUpdateRetailGoodsDialogBinding.inflate(inflater, container, false);
        View view = mRetailGoodsDialogBinding.getRoot();

        mRetailGoodsViewModel = new ViewModelProvider(this).get(RetailGoodsViewModel.class);
        mCommunicateViewModel = new ViewModelProvider(getActivity()).get(CommunicateViewModel.class);
        mBundle = getArguments();
        updateInformationRetailGoods();
        unit();
        setUpButtons();

        return view;
    }

    private void setUpButtons() {
        mRetailGoodsDialogBinding.btnFunctionUpdateRetailGoods.setOnClickListener(this);
        mRetailGoodsDialogBinding.btnFunctionInsertRetailGoods.setOnClickListener(this);
        mRetailGoodsDialogBinding.btnFunctionCancelRetailGoods.setOnClickListener(this);
    }

    private void unit() {
        ArrayAdapter<String> arrayAdapterItemsMonth = new ArrayAdapter<String>(getContext(),
                R.layout.dropdown_item, Constants.ITEMS_MONTH);
        ArrayAdapter<String> arrayAdapterItemsContinent = new ArrayAdapter<String>(getContext(),
                R.layout.dropdown_item,Constants.ITEMS_CONTINENT);

        mRetailGoodsDialogBinding.insertAutoContinent.setAdapter(arrayAdapterItemsContinent);
        mRetailGoodsDialogBinding.insertAutoMonth.setAdapter(arrayAdapterItemsMonth);

        listPriceRetailGoods[0] = mRetailGoodsDialogBinding.insertAutoMonth.getText().toString();
        listPriceRetailGoods[1] = mRetailGoodsDialogBinding.insertAutoContinent.getText().toString();

        mRetailGoodsDialogBinding.insertAutoMonth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listPriceRetailGoods[0] = adapterView.getItemAtPosition(i).toString();
            }
        });

        mRetailGoodsDialogBinding.insertAutoContinent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listPriceRetailGoods[1] = adapterView.getItemAtPosition(i).toString();
            }
        });

        setCancelable(false);
    }

    private void updateInformationRetailGoods() {
        if(mBundle != null){
            mRetailGoods = (RetailGoods) mBundle.getSerializable(Constants.RETAIL_GOODS_UPDATE);

            mRetailGoodsDialogBinding.insertAutoMonth.setText(mRetailGoods.getMonth());
            mRetailGoodsDialogBinding.insertAutoContinent.setText(mRetailGoods.getContinent());
            Objects.requireNonNull(mRetailGoodsDialogBinding.tfPolRetailGoods.getEditText()).setText(mRetailGoods.getPol());
            Objects.requireNonNull(mRetailGoodsDialogBinding.tfPodRetailGoods.getEditText()).setText(mRetailGoods.getPod());
            Objects.requireNonNull(mRetailGoodsDialogBinding.tfDimRetailGoods.getEditText()).setText(mRetailGoods.getDim());
            Objects.requireNonNull(mRetailGoodsDialogBinding.tfGrossRetailGoods.getEditText()).setText(mRetailGoods.getGrossweight());
            Objects.requireNonNull(mRetailGoodsDialogBinding.tfTypeofcargoRetailGoods.getEditText()).setText(mRetailGoods.getTypeofcargo());
            Objects.requireNonNull(mRetailGoodsDialogBinding.tfOceanAirfreightRetailGoods.getEditText()).setText(mRetailGoods.getOceanfreight());
            Objects.requireNonNull(mRetailGoodsDialogBinding.tfLocalchargeAirImport.getEditText()).setText(mRetailGoods.getLocalcharge());
            Objects.requireNonNull(mRetailGoodsDialogBinding.tfCarrierRetailGoods.getEditText()).setText(mRetailGoods.getCarrier());
            Objects.requireNonNull(mRetailGoodsDialogBinding.tfScheduleRetailGoods.getEditText()).setText(mRetailGoods.getSchedule());
            Objects.requireNonNull(mRetailGoodsDialogBinding.tfTfTransitTimeRetailGoods.getEditText()).setText(mRetailGoods.getTransittime());
            Objects.requireNonNull(mRetailGoodsDialogBinding.tfValidRetailGoods.getEditText()).setText(mRetailGoods.getValid());
            Objects.requireNonNull(mRetailGoodsDialogBinding.tfNotesRetailGoods.getEditText()).setText(mRetailGoods.getNote());


        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_function_update_retail_goods:
                updateRetailGoods();
                dismiss();
                break;
            case  R.id.btn_function_insert_retail_goods:
                insertRetailGoods();
                dismiss();
                break;
            case R.id.btn_function_cancel_retail_goods:
                dismiss();
                break;
        }
    }

    private void insertRetailGoods() {
        String strPol = mRetailGoodsDialogBinding.tfPolRetailGoods.getEditText().getText().toString();
        String strPod = mRetailGoodsDialogBinding.tfPodRetailGoods.getEditText().getText().toString();
        String strDim = mRetailGoodsDialogBinding.tfDimRetailGoods.getEditText().getText().toString();
        String strGross = mRetailGoodsDialogBinding.tfGrossRetailGoods.getEditText().getText().toString();
        String strType = mRetailGoodsDialogBinding.tfTypeofcargoRetailGoods.getEditText().getText().toString();
        String strOceanFreight = mRetailGoodsDialogBinding.tfOceanAirfreightRetailGoods.getEditText().getText().toString();
        String strLocalCharge = mRetailGoodsDialogBinding.tfLocalchargeAirImport.getEditText().getText().toString();
        String strCarrier = mRetailGoodsDialogBinding.tfCarrierRetailGoods.getEditText().getText().toString();
        String strSchedule = mRetailGoodsDialogBinding.tfScheduleRetailGoods.getEditText().getText().toString();
        String strTransittime = mRetailGoodsDialogBinding.tfTfTransitTimeRetailGoods.getEditText().getText().toString();
        String strValid = mRetailGoodsDialogBinding.tfValidRetailGoods.getEditText().getText().toString();
        String strNotes = mRetailGoodsDialogBinding.tfNotesRetailGoods.getEditText().getText().toString();

        mCommunicateViewModel.makeChanges();
        Call<RetailGoods> call = mRetailGoodsViewModel.insertRetailGoodsExport(strPol, strPod, strDim, strGross, strType,
                strOceanFreight, strLocalCharge, strCarrier, strSchedule, strTransittime, strValid, strNotes,
                listPriceRetailGoods[0], listPriceRetailGoods[1]);
        call.enqueue(new Callback<RetailGoods>() {
            @Override
            public void onResponse(Call<RetailGoods> call, Response<RetailGoods> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getContext(), "Created Successful!!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RetailGoods> call, Throwable t) {

            }
        });
    }

    private void updateRetailGoods() {
        String strPol = Objects.requireNonNull(mRetailGoodsDialogBinding.tfPolRetailGoods.getEditText()).getText().toString();
        String strPod = Objects.requireNonNull(mRetailGoodsDialogBinding.tfPodRetailGoods.getEditText()).getText().toString();
        String strDim = Objects.requireNonNull(mRetailGoodsDialogBinding.tfDimRetailGoods.getEditText()).getText().toString();
        String strGross = Objects.requireNonNull(mRetailGoodsDialogBinding.tfGrossRetailGoods.getEditText()).getText().toString();
        String strType = Objects.requireNonNull(mRetailGoodsDialogBinding.tfTypeofcargoRetailGoods.getEditText()).getText().toString();
        String strOceanFreight = Objects.requireNonNull(mRetailGoodsDialogBinding.tfOceanAirfreightRetailGoods.getEditText()).getText().toString();
        String strLocalCharge = Objects.requireNonNull(mRetailGoodsDialogBinding.tfLocalchargeAirImport.getEditText()).getText().toString();
        String strCarrier = Objects.requireNonNull(mRetailGoodsDialogBinding.tfCarrierRetailGoods.getEditText()).getText().toString();
        String strSchedule = Objects.requireNonNull(mRetailGoodsDialogBinding.tfScheduleRetailGoods.getEditText()).getText().toString();
        String strTransittime = Objects.requireNonNull(mRetailGoodsDialogBinding.tfTfTransitTimeRetailGoods.getEditText()).getText().toString();
        String strValid = Objects.requireNonNull(mRetailGoodsDialogBinding.tfValidRetailGoods.getEditText()).getText().toString();
        String strNotes = Objects.requireNonNull(mRetailGoodsDialogBinding.tfNotesRetailGoods.getEditText()).getText().toString();

        mCommunicateViewModel.makeChanges();
        Call<RetailGoods> call = mRetailGoodsViewModel.updateRetailGoods(mRetailGoods.getStt(),
                strPol, strPod, strDim, strGross, strType, strOceanFreight, strLocalCharge, strCarrier,
                strSchedule, strTransittime, strValid, strNotes, listPriceRetailGoods[0], listPriceRetailGoods[1]);
        call.enqueue(new Callback<RetailGoods>() {
            @Override
            public void onResponse(Call<RetailGoods> call, Response<RetailGoods> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getContext(), "Update Successful!!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<RetailGoods> call, Throwable t) {

            }
        });
    }
}