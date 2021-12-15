package com.example.demoapp.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.demoapp.R;
import com.example.demoapp.databinding.ActivityPriceAiractivityBinding;
import com.example.demoapp.databinding.FragmentFclBinding;
import com.example.demoapp.view.dialog.InsertFclDialog;

public class PriceAIRActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityPriceAiractivityBinding airactivityBinding;

    private final String[] itemsMonth = {"Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7",
            "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"};

    private final String[] itemsContinent = {"Asia", "Europe", "America", "Africa", "Australia"};


    private ArrayAdapter<String> adapterItemsMonth, adapterItemsContinent;

    private String month, continent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_airactivity);

//        setAdapterItems();
//        setUpButtons();
    }
    public void setAdapterItems() {
        adapterItemsMonth = new ArrayAdapter<String>(this, R.layout.dropdown_item, itemsMonth);
        adapterItemsContinent = new ArrayAdapter<String>(this, R.layout.dropdown_item, itemsContinent);

        airactivityBinding.autoCompleteMonthSale.setAdapter(adapterItemsMonth);
        airactivityBinding.autoCompleteContinent.setAdapter(adapterItemsContinent);

        airactivityBinding.autoCompleteMonthSale.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                month = adapterView.getItemAtPosition(i).toString();
            }
        });

        airactivityBinding.autoCompleteContinent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                continent = adapterView.getItemAtPosition(i).toString();
            }
        });

    }




    public void setUpButtons() {

        airactivityBinding.fragmentFclFab.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_fcl_fab:
                DialogFragment dialogFragment = InsertFclDialog.insertDialog();
                dialogFragment.show(getSupportFragmentManager(), "Insert Dialog");
                break;
            case R.id.radio_all:

                break;
        }
    }
}