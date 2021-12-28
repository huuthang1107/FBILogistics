package com.example.demoapp.view.activity.sale;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.demoapp.R;
import com.example.demoapp.adapter.PriceListAIRAdapter;
import com.example.demoapp.databinding.ActivityTablePriceAirBinding;
import com.example.demoapp.model.Air;
import com.example.demoapp.view.dialog.air.InsertAirDialog;
import com.example.demoapp.viewmodel.AirViewModel;

import java.util.ArrayList;
import java.util.List;

public class TablePriceAirActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityTablePriceAirBinding tablePriceAirBinding;
    private final String[] itemsMonth = {"Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7",
            "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"};

    private final String[] itemsContinent = {"Asia", "Europe", "America", "Africa", "Australia"};

    private ArrayAdapter<String> adapterItemsMonth, adapterItemsContinent;
    private String month = "";
    private String continent = "";
    PriceListAIRAdapter priceListAdapter;

    private AirViewModel mAirViewModel;

    private List<Air> airList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tablePriceAirBinding = ActivityTablePriceAirBinding.inflate(getLayoutInflater());
        View view = tablePriceAirBinding.getRoot();
        setContentView(view);

        mAirViewModel = new ViewModelProvider(this).get(AirViewModel.class);
        priceListAdapter = new PriceListAIRAdapter(getApplicationContext());

        mAirViewModel.getLclList().observe(this, air -> {
            priceListAdapter.setDataAir(air);
        });

        getDataAIR();
        setAdapterItems();
        setUpButtons();
        search();

    }

    private void search() {

    }

    private void setUpButtons() {
        tablePriceAirBinding.fragmentFclFab.setOnClickListener(this);
    }

    private void setAdapterItems() {
        adapterItemsMonth = new ArrayAdapter<String>(getApplicationContext(), R.layout.dropdown_item, itemsMonth);
        adapterItemsContinent = new ArrayAdapter<String>(getApplicationContext(), R.layout.dropdown_item, itemsContinent);

        tablePriceAirBinding.autoCompleteMonth.setAdapter(adapterItemsMonth);
        tablePriceAirBinding.autoCompleteContinent.setAdapter(adapterItemsContinent);

        tablePriceAirBinding.autoCompleteMonth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                month = adapterView.getItemAtPosition(i).toString();
                setDataForRecyclerView(month, continent);
            }
        });

        tablePriceAirBinding.autoCompleteContinent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                continent = adapterView.getItemAtPosition(i).toString();
                setDataForRecyclerView(month, continent);
            }
        });

    }

    private void getDataAIR() {
        airList = new ArrayList<>();
        mAirViewModel.getLclList().observe(this, detailsPojoAir -> {
            this.airList = detailsPojoAir;
        });
    }
    public void setDataForRecyclerView(String m, String c) {
        if (!m.isEmpty() && !c.isEmpty()) {
            tablePriceAirBinding.priceListRcv.setHasFixedSize(true);

            tablePriceAirBinding.priceListRcv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            priceListAdapter.setDataAir(prepareDataForRecyclerView(m,c));

            tablePriceAirBinding.priceListRcv.setAdapter(priceListAdapter);
        }
    }

    private List<Air> prepareDataForRecyclerView(String m, String c) {
        List<Air> list = new ArrayList<>();

        for (Air a : airList) {
            if (a.getMonth().equalsIgnoreCase(m) && a.getContinent().equalsIgnoreCase(c)) {
                list.add(a);
            }
        }
        return list;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_fcl_fab:
                DialogFragment dialogFragment = InsertAirDialog.insertDiaLogAIR();
                dialogFragment.show(getSupportFragmentManager(), "Insert Dialog");

                break;
        }
    }

}