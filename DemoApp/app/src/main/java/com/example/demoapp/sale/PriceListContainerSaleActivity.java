package com.example.demoapp.sale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import com.example.demoapp.R;
import com.example.demoapp.model.PriceListContainerSale;
import com.example.demoapp.my_interface.IClickItemContainerPriceList;
import com.example.demoapp.professional.ActivityPriceList;

import java.util.ArrayList;

public class PriceListContainerSaleActivity extends AppCompatActivity {



    ArrayList<PriceListContainerSale> containerListSale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_list_container_sale);

        RecyclerView rvContainer = findViewById(R.id.activity_price_list_container_sale_rcvPrice);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        rvContainer.addItemDecoration(itemDecoration);

        containerListSale = new ArrayList<>();
        containerListSale.add(new PriceListContainerSale("1","Hai phong","Uc",
                "200000","300000","400000","5000000","1",
                "TT","12010210","GP"));
        containerListSale.add(new PriceListContainerSale("1","Hai phong","Uc",
                "200000","300000","400000","5000000","1",
                "TT","12010210","GP"));

        PriceListContainerAdapter adapter = new PriceListContainerAdapter(containerListSale, new IClickItemContainerPriceList() {
            @Override
            public void onClickItemPriceContainer() {
              onClickGoToDetail();

            }
        });
        rvContainer.setAdapter(adapter);
        rvContainer.setLayoutManager(new LinearLayoutManager(this));

    }
    private void onClickGoToDetail(){
        Intent intent = new Intent(this, ActivityPriceList.class);
        startActivity(intent);
    }
}