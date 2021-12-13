package com.example.demoapp.fragment_sale;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.demoapp.R;


//import com.example.demoapp.databinding.FragmentHangkhongBinding;
import com.example.demoapp.professional.ActivityPriceListAIR;

public class DuongHangKhongFragment extends Fragment implements View.OnClickListener {

//    private FragmentHangkhongBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//            binding = FragmentHangkhongBinding.inflate(getLayoutInflater());
//            return binding.getRoot();
        return inflater.inflate(R.layout.fragment_hangkhong, container, false);
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        setOnClick();
//
//    }
//
//    private void setOnClick() {
//        binding.cvCargoNK.setOnClickListener(this);
//        binding.cvCargoXK.setOnClickListener(this);
//        binding.cvExpressNK.setOnClickListener(this);
//        binding.cvExpressXK.setOnClickListener(this);
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cv_cargoNK:
//                Intent intent = new Intent(getContext(), ActivityPriceListAIR.class);

        }

    }
}
