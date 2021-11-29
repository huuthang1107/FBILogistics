package com.example.demoapp.fragment_sale;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.demoapp.MainActivity;
import com.example.demoapp.R;

public class DuongBienFragment extends Fragment implements  View.OnClickListener {
    private CardView cvContainer;
    private ImageView iv_ContainerNK;
    private  View view;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_duongbien,container,false);

        cvContainer = view.findViewById(R.id.cv_contaierNK);
        iv_ContainerNK = view.findViewById(R.id.imv_containerNK);


        cvContainer.setOnClickListener(this);
        iv_ContainerNK.setOnClickListener(this);


        return  view;
    }

    @Override
    public void onClick(View v) {
//        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//        FCLFragment fclFragment = new FCLFragment();
//        fragmentTransaction.replace(R.id.duongbien, fclFragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();


    }
}
