package com.example.demoapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.demoapp.fragment_pricelistAIR.PriceListAmericaFragment;
import com.example.demoapp.fragment_pricelistAIR.PriceListAsiaFragment;
import com.example.demoapp.fragment_pricelistAIR.PriceListEuropeFragment;

public class PriceListViewAdapterLCL extends FragmentStateAdapter {

    public PriceListViewAdapterLCL(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
       switch (position) {
           case 0:
               return new PriceListAsiaFragment();
           case 1:
               return new PriceListEuropeFragment();
           case 2:
               return new PriceListAmericaFragment();
           default:
               return new PriceListAsiaFragment();
       }
    }

    @Override
    public int getItemCount() {

        return 3;
    }
}
