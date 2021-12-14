package com.example.demoapp.fragment_sale;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.demoapp.R;



import com.example.demoapp.professional.ActivityPriceListAIR;

public class DuongHangKhongFragment extends Fragment implements View.OnClickListener {

    private CardView cvcargoNk;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_hangkhong, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cvcargoNk = view.findViewById(R.id.cv_cargoNK);

        cvcargoNk.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cv_cargoNK:
                Intent intent = new Intent(getContext(), ActivityPriceListAIR.class);
                startActivity(intent);

        }

    }
}
