package com.example.demoapp.view.detail;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.demoapp.Constant.Constant;
import com.example.demoapp.databinding.DialogFclDetailBinding;
import com.example.demoapp.model.Fcl;

public class FragmentFclDetail extends DialogFragment {
    private DialogFclDetailBinding binding;
    private Bundle bundle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DialogFclDetailBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        bundle = getArguments();
        if(bundle != null){
            Fcl fcl = (Fcl) bundle.getSerializable(Constant.FCL_OBJECT);
            binding.tvRowPriceAsiaStt.setText(fcl.getStt());
            binding.tvRowPriceAsiaPol.setText(fcl.getPol());
            binding.tvRowPriceAsiaPod.setText(fcl.getPod());
            binding.tvRowPriceAsiaOf20.setText(fcl.getOf20());
            binding.tvRowPriceAsiaOf40.setText(fcl.getOf40());
            binding.tvRowPriceAsiaSu20.setText(fcl.getSu20());
            binding.tvRowPriceAsiaSu40.setText(fcl.getSu40());
            binding.tvRowPriceAsiaLine.setText(fcl.getLinelist());
            binding.tvRowPriceAsiaNotes1.setText(fcl.getNotes());
            binding.tvRowPriceAsiaValid.setText(fcl.getValid());
            binding.tvRowPriceAsiaNotes2.setText(fcl.getNotes2());
        }
        return view;
    }

    public static FragmentFclDetail getInstance(){
        return new FragmentFclDetail();
    }
}
