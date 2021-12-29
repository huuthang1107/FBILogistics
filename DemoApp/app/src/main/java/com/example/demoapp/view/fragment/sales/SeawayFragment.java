package com.example.demoapp.view.fragment.sales;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.demoapp.R;
import com.example.demoapp.databinding.FragmentSeawayBinding;
import com.example.demoapp.view.activity.sale.ContainerActivity;


public class SeawayFragment extends Fragment implements View.OnClickListener{

    private FragmentSeawayBinding mSeawayBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mSeawayBinding = FragmentSeawayBinding.inflate(inflater, container , false);
        View view = mSeawayBinding.getRoot();

        event();
        return view;
    }

    private void event() {
        mSeawayBinding.cvContainerXK.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), ContainerActivity.class);
        startActivity(intent);

    }
}