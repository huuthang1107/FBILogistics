package com.example.demoapp.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.demoapp.databinding.FragmentAirRouteBinding;
import com.example.demoapp.view.activity.PriceAIRActivity;

public class AirRouteSaleFragment extends Fragment implements View.OnClickListener {

    FragmentAirRouteBinding airRouteBinding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        airRouteBinding = FragmentAirRouteBinding.inflate(inflater, container, false);
        View view = airRouteBinding.getRoot();
        events();
        return view;
    }

    private void events(){
        airRouteBinding.cvCargoNK.setOnClickListener(this);
        airRouteBinding.cvExpressNK.setOnClickListener(this);
        airRouteBinding.cvCargoXK.setOnClickListener(this);
        airRouteBinding.cvCargoXK.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), PriceAIRActivity.class);
        startActivity(intent);

    }
}