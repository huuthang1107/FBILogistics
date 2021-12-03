package com.example.demoapp.sale;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.demoapp.fragment_pricelistSale.PriceListAsiaSale;
public class PriceListViewAdapterSales extends FragmentStateAdapter {

    public PriceListViewAdapterSales(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new PriceListAsiaSale();
//            case 1:
//                return new PriceListEuropeSale();
//            case 2:
//                return new PriceListAmericaSale();
            default:
                return new PriceListAsiaSale();
        }
    }

    @Override
    public int getItemCount() {
        // there are 3 tabs
        return 3;
    }
}
