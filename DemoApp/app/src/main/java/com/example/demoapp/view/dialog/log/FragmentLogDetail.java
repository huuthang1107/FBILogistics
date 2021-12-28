package com.example.demoapp.view.dialog.log;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.demoapp.databinding.FragmentLogDetailBinding;
import com.example.demoapp.model.Log;
import com.example.demoapp.utilities.Constants;


public class FragmentLogDetail extends DialogFragment {

    private FragmentLogDetailBinding mDetailBinding;
    private Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mDetailBinding = FragmentLogDetailBinding.inflate(inflater, container, false);
        View view = mDetailBinding.getRoot();
        bundle = getArguments();
        if(bundle != null){
            Log log = (Log) bundle.getSerializable(Constants.LOG_OBJECT);
            mDetailBinding.tvRowPriceLogStt.setText(log.getStt());
            mDetailBinding.tvRowPriceLogTenhang.setText(log.getTenhang());
            mDetailBinding.tvRowPriceLogHscode.setText(log.getHscode());
            mDetailBinding.tvRowPriceLogCongdung.setText(log.getCongdung());
            mDetailBinding.tvRowPriceLogHinhanh.setText(log.getHinhanh());
            mDetailBinding.tvRowPriceLogCangdi.setText(log.getCangdi());
            mDetailBinding.tvRowPriceLogCangden.setText(log.getCangden());
            mDetailBinding.tvRowPriceLogLoaihang.setText(log.getLoaihang());
            mDetailBinding.tvRowPriceLogSoluongcuthe.setText(log.getSoluongcuthe());
            mDetailBinding.tvRowPriceLogYeucaudacbiet.setText(log.getYeucaudacbiet());
            mDetailBinding.tvRowPriceLogValid.setText(log.getValid());


        }
        return view;
    }
    public static FragmentLogDetail getInstance(){

        return new FragmentLogDetail();
    }
}