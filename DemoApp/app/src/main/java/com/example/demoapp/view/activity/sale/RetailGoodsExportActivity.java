package com.example.demoapp.view.activity.sale;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.demoapp.R;
import com.example.demoapp.adapter.sale.PriceListRetailGoodsSaleAdapter;
import com.example.demoapp.databinding.ActivityRetailGoodsExportBinding;
import com.example.demoapp.model.RetailGoods;
import com.example.demoapp.utilities.Constants;
import com.example.demoapp.viewmodel.CommunicateViewModel;
import com.example.demoapp.viewmodel.RetailGoodsViewModel;

import java.util.ArrayList;
import java.util.List;

public class RetailGoodsExportActivity extends AppCompatActivity {
    private ActivityRetailGoodsExportBinding mBinding;


    private String month = "";
    private String continent = "";
    private PriceListRetailGoodsSaleAdapter mPriceListRetailGoddsAdapter;
    private RetailGoodsViewModel mRetailGoodsViewModel;
    private List<RetailGoods> retailGoodsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityRetailGoodsExportBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();

        mPriceListRetailGoddsAdapter = new PriceListRetailGoodsSaleAdapter(this);
        mRetailGoodsViewModel = new ViewModelProvider(this).get(RetailGoodsViewModel.class);
        CommunicateViewModel mCommunicateViewModel = new ViewModelProvider(this).get(CommunicateViewModel.class);
        mCommunicateViewModel.needReloading.observe(this, needLoading ->{
            if(needLoading){
                Log.d("onresume", String.valueOf(needLoading.toString()));
                onResume();
            }
        });
        getDataRetailGoods();
        setAdapterItems();
        setContentView(view);
    }
    private void setAdapterItems() {
        ArrayAdapter<String> adapterItemsMonth = new ArrayAdapter<String>(getApplicationContext(), R.layout.dropdown_item, Constants.ITEMS_MONTH);
        ArrayAdapter<String> adapterItemsContinent = new ArrayAdapter<String>(getApplicationContext(), R.layout.dropdown_item, Constants.ITEMS_CONTINENT);

        mBinding.autoCompleteMonthRetail.setAdapter(adapterItemsMonth);
        mBinding.autoCompleteContinentRetail.setAdapter(adapterItemsContinent);

        mBinding.autoCompleteMonthRetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                month = adapterView.getItemAtPosition(i).toString();
                setDataForRecyclerView(month, continent);

            }
        });

        mBinding.autoCompleteContinentRetail.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                continent = adapterView.getItemAtPosition(i).toString();
                setDataForRecyclerView(month, continent);
            }
        });

    }

    private void setDataForRecyclerView(String m, String c) {
        if(!m.isEmpty() && !c.isEmpty()){
            mPriceListRetailGoddsAdapter.setDataRetailGoods(prepareDataForRecycleView(m, c));
            mBinding.priceListRcvRetail.setAdapter(mPriceListRetailGoddsAdapter);
            mBinding.priceListRcvRetail.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        }
    }

    private List<RetailGoods> prepareDataForRecycleView(String m, String c) {
        List<RetailGoods> list = new ArrayList<>();
        try {
            for(RetailGoods rg : retailGoodsList){
                if(rg.getMonth().equalsIgnoreCase(m) && rg.getContinent().equalsIgnoreCase(c)){
                    list.add(rg);
                }
            }
        }catch (NullPointerException nullPointerException){
            Toast.makeText(getApplicationContext(), nullPointerException.toString(), Toast.LENGTH_LONG).show();
        }

        return list;
    }



    private void getDataRetailGoods() {
        retailGoodsList = new ArrayList<>();
        mRetailGoodsViewModel.getRetailGoodsList().observe(this, detailsPojoRetailGoods ->{
            this.retailGoodsList = sortRetailGoods(detailsPojoRetailGoods);
        });
    }

    private List<RetailGoods> sortRetailGoods(List<RetailGoods> list) {
        List<RetailGoods> result = new ArrayList<>();
        for (int i = list.size()-1; i>=0; i--){
            result.add(list.get(i));
        }
        return result;
    }
    public List<RetailGoods> preparedataForResume(String m, String c, List<RetailGoods> list){
        List<RetailGoods> subList = new ArrayList<>();
        try {
            for(RetailGoods rg : retailGoodsList){
                if(rg.getMonth().equalsIgnoreCase(m) && rg.getContinent().equalsIgnoreCase(c)){
                    subList.add(rg);
                }
            }
        }catch (NullPointerException nullPointerException){
            Toast.makeText(getApplicationContext(), nullPointerException.toString(), Toast.LENGTH_LONG).show();
        }
        return subList;
    }

    @Override
    public void onResume() {
        super.onResume();
        mRetailGoodsViewModel.getRetailGoodsList().observe(this, retailGoods ->{
            mPriceListRetailGoddsAdapter.setDataRetailGoods(preparedataForResume(month,continent, retailGoods));
        });
        mBinding.priceListRcvRetail.setAdapter(mPriceListRetailGoddsAdapter);
    }
}