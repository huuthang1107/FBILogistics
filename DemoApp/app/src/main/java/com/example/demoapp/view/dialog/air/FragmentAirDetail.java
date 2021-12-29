package com.example.demoapp.view.dialog.air;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.demoapp.databinding.FragmentAirDialogBinding;
import com.example.demoapp.model.Air;
import com.example.demoapp.model.Log;
import com.example.demoapp.utilities.Constants;
import com.example.demoapp.view.dialog.log.FragmentLogDetail;


public class FragmentAirDetail extends DialogFragment {

    private FragmentAirDialogBinding mAirDialogBinding;
    private Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mAirDialogBinding = FragmentAirDialogBinding.inflate(inflater, container, false);
        View view = mAirDialogBinding.getRoot();

        bundle = getArguments();
        if (bundle != null) {
            Air air = (Air) bundle.getSerializable(Constants.AIR_OBJECT);
            mAirDialogBinding.tvRowPriceAsiaAirStt.setText(air.getStt());
            mAirDialogBinding.tvRowPriceAsiaAirAol.setText(air.getAol());
            mAirDialogBinding.tvRowPriceAsiaAirAod.setText(air.getAod());
            mAirDialogBinding.tvRowPriceAsiaAirDim.setText(air.getDim());
            mAirDialogBinding.tvRowPriceAsiaAirGrossweight.setText(air.getGrossweight());
            mAirDialogBinding.tvRowPriceAsiaAirTypeofcargo.setText(air.getTypeofcargo());
            mAirDialogBinding.tvRowPriceAsiaAirFreight.setText(air.getAirfreight());
            mAirDialogBinding.tvRowPriceAsiaAirSurcharge.setText(air.getSurcharge());
            mAirDialogBinding.tvRowPriceAsiaAirAirlines.setText(air.getAirlines());
            mAirDialogBinding.tvRowPriceAsiaAirSchedule.setText(air.getSchedule());
            mAirDialogBinding.tvRowPriceAsiaAirTransittime.setText(air.getTransittime());
            mAirDialogBinding.tvRowPriceAsiaAirValid.setText(air.getValid());
            mAirDialogBinding.tvRowPriceAsiaAirNote.setText(air.getNote());

        }
        return view;
    }
    public static FragmentAirDetail getInstance(){

        return new FragmentAirDetail();
    }
}