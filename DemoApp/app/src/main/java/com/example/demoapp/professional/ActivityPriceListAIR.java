package com.example.demoapp.professional;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import com.example.demoapp.R;
import com.example.demoapp.adapter.PriceListViewAdapterLCL;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ActivityPriceListAIR extends AppCompatActivity {

    private  TabLayout tabLayout;
    private  ViewPager2 viewPager;
    private PriceListViewAdapterLCL priceListViewAdapterLCL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_list_air);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager_price);
        priceListViewAdapterLCL = new PriceListViewAdapterLCL(this);
        viewPager.setAdapter(priceListViewAdapterLCL);
        viewPager.setUserInputEnabled(false);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("Asia");
                    break;
                case 1:
                    tab.setText("Europe");
                    break;
                case 2:
                    tab.setText("America");
                    break;
            }
        }).attach();
    }
}