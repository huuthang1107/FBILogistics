package com.example.demoapp.view.dialog.imp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.demoapp.databinding.DialogImportDetailBinding;
import com.example.demoapp.model.Import;
import com.example.demoapp.utilities.Constants;

public class FragmentImportDetail extends DialogFragment {
    private DialogImportDetailBinding binding;
    private Bundle bundle;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       binding = DialogImportDetailBinding.inflate(inflater, container, false);
       View view = binding.getRoot();

        bundle = getArguments();
        if(bundle != null){
            Import imp = (Import) bundle.getSerializable(Constants.IMPORT_OBJECT);
            binding.tvRowPriceImportStt.setText(imp.getStt());
            binding.tvRowPriceImportPol.setText(imp.getPol());
            binding.tvRowPriceImportPod.setText(imp.getPod());
            binding.tvRowPriceImportOf20.setText(imp.getOf20());
            binding.tvRowPriceImportOf40.setText(imp.getOf40());
            binding.tvRowPriceImportSurcharge.setText(imp.getSurcharge());
            binding.tvRowPriceImportSchedule.setText(imp.getSchedule());
            binding.tvRowPriceImportTotal.setText(imp.getTotalFreight());
            binding.tvRowPriceImportCarrier.setText(imp.getCarrier());
            binding.tvRowPriceImportSchedule.setText(imp.getSchedule());
            binding.tvRowPriceImportTransit.setText(imp.getTransitTime());
            binding.tvRowPriceImportFree.setText(imp.getFreeTime());
            binding.tvRowPriceImportValid.setText(imp.getValid());
            binding.tvRowPriceImportNote.setText(imp.getNote());
        }

       return view;
    }

    public static FragmentImportDetail getInstance(){
        return new FragmentImportDetail();
    }
}
