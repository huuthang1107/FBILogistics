package com.example.demoapp.professional;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import com.example.demoapp.R;
import com.example.demoapp.adapter.PriceListViewAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ActivityPriceList extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager;
    private PriceListViewAdapter mPriceListViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_list);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager_price);

        mPriceListViewAdapter = new PriceListViewAdapter(this);
        viewPager.setAdapter(mPriceListViewAdapter);
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