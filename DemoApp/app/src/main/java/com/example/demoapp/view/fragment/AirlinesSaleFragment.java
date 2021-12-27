package com.example.demoapp.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.demoapp.R;
import com.example.demoapp.databinding.FragmentAirlinesBinding;
import com.example.demoapp.databinding.FragmentLclBinding;
import com.example.demoapp.view.activity.TablePriceAirActivity;

public class AirlinesSaleFragment extends Fragment implements  View.OnClickListener{

    private FragmentAirlinesBinding airlinesBinding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        airlinesBinding = FragmentAirlinesBinding.inflate(inflater, container, false);
        View view = airlinesBinding.getRoot();

        event();
        return  view;
    }

    /**
     * Event handler for cardview
     */
    private void event(){
        airlinesBinding.cvCargoNK.setOnClickListener(this);
        airlinesBinding.cvCargoXK.setOnClickListener(this);
        airlinesBinding.cvExpressNK.setOnClickListener(this);
        airlinesBinding.cvExpressXK.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), TablePriceAirActivity.class);
        startActivity(intent);
    }
}