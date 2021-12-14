package com.example.demoapp.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.demoapp.R;
import com.example.demoapp.databinding.FragmentSeaRoadBinding;


public class SeaRoadFragment extends Fragment implements View.OnClickListener {

    private CardView cvContainerXK;
    private ImageView iv_ContainerXK;
    private  View view;
    public FragmentSeaRoadBinding seaRoadBinding;

    public SeaRoadFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        seaRoadBinding =  FragmentSeaRoadBinding.inflate(inflater, container, false);
        return seaRoadBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cvContainerXK = view.findViewById(R.id.cv_containerXK);
        iv_ContainerXK = view.findViewById(R.id.iv_containerXK);


        cvContainerXK.setOnClickListener(this);
        iv_ContainerXK.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

    }
}