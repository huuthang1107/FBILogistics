package com.example.demoapp.professional;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.demoapp.fragment_pricelist.PriceListAmerica;
import com.example.demoapp.fragment_pricelist.PriceListAsia;
import com.example.demoapp.fragment_pricelist.PriceListEurope;

public class PriceListViewAdapter extends FragmentStateAdapter {

    public PriceListViewAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new PriceListAsia();
            case 1:
                return new PriceListEurope();
            case 2:
                return new PriceListAmerica();
            default:
                return new PriceListAsia();
        }
    }

    @Override
    public int getItemCount() {
        // there are 3 tabs
        return 3;
    }
}
