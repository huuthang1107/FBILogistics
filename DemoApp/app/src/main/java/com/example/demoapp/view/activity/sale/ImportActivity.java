package com.example.demoapp.view.activity.sale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.demoapp.R;
import com.example.demoapp.adapter.PriceListImportAdapter;
import com.example.demoapp.databinding.ActivityImportBinding;
import com.example.demoapp.model.Import;
import com.example.demoapp.view.dialog.imp.InsertImportDialog;
import com.example.demoapp.viewmodel.CommunicateViewModel;
import com.example.demoapp.viewmodel.ImportViewModel;

import java.util.ArrayList;
import java.util.List;

public class ImportActivity extends AppCompatActivity implements View.OnClickListener {

    private final String[] itemsMonth = {"Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7",
            "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"};

    private final String[] itemsContinent = {"Asia", "Europe", "America", "Africa", "Australia"};

    private ArrayAdapter<String> adapterItemsMonth, adapterItemsContinent;

    private String month = "";
    private String continent = "";
    private String radioItem = "All";

    List<Import> listPriceList = new ArrayList<>();
    private PriceListImportAdapter priceListAdapter;
    private ImportViewModel mImportViewModel;
    private ActivityImportBinding mImportBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mImportBinding = ActivityImportBinding.inflate(getLayoutInflater());
        View view = mImportBinding.getRoot();
        priceListAdapter = new PriceListImportAdapter(this);
        mImportViewModel = new ViewModelProvider(this).get(ImportViewModel.class);

        CommunicateViewModel mCommunicateViewModel = new ViewModelProvider(this).get(CommunicateViewModel.class);

        mCommunicateViewModel.needReloading.observe(this, needLoading ->{
            if(needLoading){
                onResume();
            }
        });

        setAdapterItems();
        setUpButtons();
        getAllData();
        setContentView(view);
    }

    public void setAdapterItems() {
        adapterItemsMonth = new ArrayAdapter<String>(this, R.layout.dropdown_item, itemsMonth);
        adapterItemsContinent = new ArrayAdapter<String>(this, R.layout.dropdown_item, itemsContinent);

        mImportBinding.autoCompleteMonth.setAdapter(adapterItemsMonth);
        mImportBinding.autoCompleteContinent.setAdapter(adapterItemsContinent);

        mImportBinding.autoCompleteMonth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                month = adapterView.getItemAtPosition(i).toString();
                setDataForRecyclerView(month, continent, radioItem);
            }
        });

        mImportBinding.autoCompleteContinent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                continent = adapterView.getItemAtPosition(i).toString();
                setDataForRecyclerView(month, continent, radioItem);
            }
        });

    }

    /**
     * this method will set data for recycler view
     *
     * @param m month
     * @param c continent
     * @param r radio
     */
    public void setDataForRecyclerView(String m, String c, String r) {
        if (!m.isEmpty() && !c.isEmpty()) {
            priceListAdapter.setImports(prepareDataForRecyclerView(month,continent,radioItem));
            mImportBinding.priceListRcv.setLayoutManager(new LinearLayoutManager(this));
            mImportBinding.priceListRcv.setAdapter(priceListAdapter);
        }
    }

    /**
     * this method will filter list data by month and continent
     *
     * @param m month
     * @param c continent
     * @return get list by month and continent
     */
    public List<Import> prepareDataForRecyclerView(String m, String c, String r) {
        // reset a list when user choose different
        List<Import> list = new ArrayList<>();

        for (Import imp : listPriceList) {
            if (r.equalsIgnoreCase("all")) {
                if (imp.getMonth().equalsIgnoreCase(m) && imp.getContinent().equalsIgnoreCase(c)) {
                    list.add(imp);
                }
            } else {
                if (imp.getMonth().equalsIgnoreCase(m) && imp.getContinent().equalsIgnoreCase(c)
                        && imp.getType().equalsIgnoreCase(r)) {
                    list.add(imp);
                }
            }
        }
        return list;
    }
    public List<Import> prepareDataForResume(String m, String c, String r, List<Import> list) {
        // reset a list when user choose different
        List<Import> subList = new ArrayList<>();
        for (Import imp : list) {
            if (r.equalsIgnoreCase("all")) {
                if (imp.getMonth().equalsIgnoreCase(m) && imp.getContinent().equalsIgnoreCase(c)) {
                    subList.add(imp);
                }
            } else {
                if (imp.getMonth().equalsIgnoreCase(m) && imp.getContinent().equalsIgnoreCase(c)
                        && imp.getType().equalsIgnoreCase(r)) {
                    subList.add(imp);
                }
            }
        }
        return subList;
    }


    /**
     * this method will get all data from database
     */
    public void getAllData() {

        mImportViewModel.getImportList().observe(this, detailsPojoImports -> {
            this.listPriceList = detailsPojoImports;
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        priceListAdapter = new PriceListImportAdapter(this);
        mImportViewModel.getImportList().observe(this, imp -> {
            priceListAdapter.setImports( prepareDataForResume(month, continent, radioItem, imp));
        });

        mImportBinding.priceListRcv.setAdapter(priceListAdapter);
    }

    /**
     * this method will set listen for buttons
     */
    public void setUpButtons() {
        mImportBinding.fragmentFclFab.setOnClickListener(this);

        mImportBinding.radioAll.setOnClickListener(this);
        mImportBinding.radioAll.performClick();

        mImportBinding.radioGp.setOnClickListener(this);

        mImportBinding.radioFr.setOnClickListener(this);

        mImportBinding.radioRf.setOnClickListener(this);

        mImportBinding.radioHq.setOnClickListener(this);

        mImportBinding.radioOt.setOnClickListener(this);

        mImportBinding.radioTk.setOnClickListener(this);
    }

    /**
     * this method used to set event for button click
     *
     * @param view click
     */
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_fcl_fab:
                DialogFragment dialogFragment = InsertImportDialog.insertDialog();
                dialogFragment.show(getSupportFragmentManager(), "Insert Dialog");
                break;
            case R.id.radio_all:
                radioItem = mImportBinding.radioAll.getText().toString();
                setDataForRecyclerView(month, continent, radioItem);
                break;

            case R.id.radio_gp:
                radioItem = mImportBinding.radioGp.getText().toString();
                setDataForRecyclerView(month, continent, radioItem);
                break;

            case R.id.radio_fr:
                radioItem = mImportBinding.radioFr.getText().toString();
                setDataForRecyclerView(month, continent, radioItem);
                break;

            case R.id.radio_rf:
                radioItem = mImportBinding.radioRf.getText().toString();
                setDataForRecyclerView(month, continent, radioItem);
                break;

            case R.id.radio_hq:
                radioItem = mImportBinding.radioHq.getText().toString();
                setDataForRecyclerView(month, continent, radioItem);
                break;

            case R.id.radio_ot:
                radioItem = mImportBinding.radioOt.getText().toString();
                setDataForRecyclerView(month, continent, radioItem);
                break;
            case R.id.radio_tk:
                radioItem = mImportBinding.radioTk.getText().toString();
                setDataForRecyclerView(month, continent, radioItem);
                break;
        }
    }
}