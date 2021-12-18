package com.example.demoapp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.demoapp.R;
import com.example.demoapp.databinding.FragmentDomBinding;
import com.example.demoapp.view.activity.IETruckingDomActivity;

public class FragmentDOM extends Fragment implements View.OnClickListener {
    FragmentDomBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDomBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.fragmentDomBtnType1.setOnClickListener(this);
        binding.fragmentDomBtnType2.setOnClickListener(this);
        binding.fragmentDomBtnType3.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fragment_dom_btn_type1:
                Intent intent = new Intent(getActivity(), IETruckingDomActivity.class);
                startActivity(intent);
                break;
        }
    }
}
