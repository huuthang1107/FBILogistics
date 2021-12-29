package com.example.demoapp.view.fragment.sales;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.demoapp.R;
import com.example.demoapp.databinding.FragmentRoadBinding;
import com.example.demoapp.view.activity.sale.LogActivity;


public class RoadFragment extends Fragment implements View.OnClickListener {

    private FragmentRoadBinding mRoadBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRoadBinding = FragmentRoadBinding.inflate(inflater, container, false);
        View view = mRoadBinding.getRoot();

        event();
        return view;
    }

    private void event() {
        mRoadBinding.containerXK.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), LogActivity.class);
        startActivity(intent);
    }
}