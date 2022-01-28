package com.example.demoapp.view.activity.sale;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.demoapp.R;
import com.example.demoapp.adapter.sale.PriceListImportLclSaleAdapter;
import com.example.demoapp.databinding.ActivityImportLclSaleBinding;
import com.example.demoapp.model.ImportLcl;
import com.example.demoapp.utilities.Constants;
import com.example.demoapp.viewmodel.CommunicateViewModel;
import com.example.demoapp.viewmodel.ImportLclViewModel;

import java.util.ArrayList;
import java.util.List;

public class ImportLclSaleActivity extends AppCompatActivity {

    private ActivityImportLclSaleBinding binding;
    private String month = "";
    private String continent = "";

    List<ImportLcl> listPriceList = new ArrayList<>();
    private PriceListImportLclSaleAdapter priceListAdapter;
    private ImportLclViewModel mImportViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityImportLclSaleBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        priceListAdapter = new PriceListImportLclSaleAdapter(this);
        mImportViewModel = new ViewModelProvider(this).get(ImportLclViewModel.class);
        CommunicateViewModel mCommunicateViewModel = new ViewModelProvider(this).get(CommunicateViewModel.class);

        mCommunicateViewModel.needReloading.observe(this, needLoading -> {
            if (needLoading) {
                onResume();
            }
        });

        setAdapterItems();
        getAllData();
        setContentView(view);
    }
    public void setAdapterItems() {
        ArrayAdapter<String> adapterItemsMonth = new ArrayAdapter<String>(getApplicationContext(), R.layout.dropdown_item, Constants.ITEMS_MONTH);
        ArrayAdapter<String> adapterItemsContinent = new ArrayAdapter<String>(getApplicationContext(), R.layout.dropdown_item, Constants.ITEMS_CONTINENT);

        binding.autoCompleteMonth.setAdapter(adapterItemsMonth);
        binding.autoCompleteContinent.setAdapter(adapterItemsContinent);

        binding.autoCompleteMonth.setOnItemClickListener((adapterView, view, i, l) -> {
            month = adapterView.getItemAtPosition(i).toString();
            setDataForRecyclerView(month, continent);
        });

        binding.autoCompleteContinent.setOnItemClickListener((adapterView, view, i, l) -> {
            continent = adapterView.getItemAtPosition(i).toString();
            setDataForRecyclerView(month, continent);
        });

    }

    /**
     * this method will set data for recycler view
     *
     * @param m month
     * @param c continent
     */
    public void setDataForRecyclerView(String m, String c) {
        if (!m.isEmpty() && !c.isEmpty()) {
            priceListAdapter.setImports(prepareDataForRecyclerView(month, continent));
            binding.priceListRcv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            binding.priceListRcv.setAdapter(priceListAdapter);
        }
    }

    /**
     * this method will filter list data by month and continent
     *
     * @param m month
     * @param c continent
     * @return get list by month and continent
     */
    public List<ImportLcl> prepareDataForRecyclerView(String m, String c) {
        // reset a list when user choose different
        List<ImportLcl> list = new ArrayList<>();

        for (ImportLcl imp : listPriceList) {

            if (imp.getMonth().equalsIgnoreCase(m) && imp.getContinent().equalsIgnoreCase(c)) {
                list.add(imp);
            }
        }
        return list;
    }

    public List<ImportLcl> prepareDataForResume(String m, String c, List<ImportLcl> list) {
        // reset a list when user choose different
        List<ImportLcl> subList = new ArrayList<>();

        try {
            for (ImportLcl imp : list) {
                if (imp.getMonth().equalsIgnoreCase(m) && imp.getContinent().equalsIgnoreCase(c)) {
                    subList.add(imp);
                }
            }

        } catch (NullPointerException nullPointerException) {
            Toast.makeText(getApplicationContext(), nullPointerException.toString(), Toast.LENGTH_LONG).show();
        }
        return subList;
    }


    /**
     * this method will get all data from database
     */
    public void getAllData() {

        try {
            mImportViewModel.getImportList().observe(this, detailsPojoImports ->
                    this.listPriceList = detailsPojoImports);
        } catch (NullPointerException exception) {
            Toast.makeText(getApplicationContext(), exception.toString(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        priceListAdapter = new PriceListImportLclSaleAdapter(getApplicationContext());
        mImportViewModel.getImportList().observe(this, imp ->
                priceListAdapter.setImports(prepareDataForResume(month, continent, imp)));

        binding.priceListRcv.setAdapter(priceListAdapter);
    }


}