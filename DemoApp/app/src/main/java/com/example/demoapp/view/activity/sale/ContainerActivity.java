package com.example.demoapp.view.activity.sale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.demoapp.R;
import com.example.demoapp.adapter.PriceListFclAdapter;
import com.example.demoapp.databinding.ActivityContainerBinding;
import com.example.demoapp.model.Fcl;
import com.example.demoapp.utilities.Constants;
import com.example.demoapp.view.dialog.fcl.InsertFclDialog;
import com.example.demoapp.viewmodel.CommunicateViewModel;
import com.example.demoapp.viewmodel.FclViewModel;

import java.util.ArrayList;
import java.util.List;

public class ContainerActivity extends AppCompatActivity implements View.OnClickListener{

    private String month = "";
    private String continent = "";
    private String radioItem = "All";
    private ActivityContainerBinding mContainerBinding;

    private List<Fcl> listPriceList = new ArrayList<>();
    private PriceListFclAdapter priceListFclAdapter;

    private FclViewModel mFclViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContainerBinding = ActivityContainerBinding.inflate(getLayoutInflater());
        View view = mContainerBinding.getRoot();

        priceListFclAdapter = new PriceListFclAdapter(this);
        mFclViewModel = new ViewModelProvider(this).get(FclViewModel.class);
        CommunicateViewModel mCommunicateViewModel = new ViewModelProvider(this).get(CommunicateViewModel.class);

        mCommunicateViewModel.needReloading.observe(this, needLoading -> {
            if (needLoading) {
                onResume();
            }
        });

        getAllData();
        setAdapterItems();
        setUpButtons();
        setContentView(view);
    }

    private void setUpButtons() {
//        mContainerBinding.fragmentFclFab.setOnClickListener(this);

        mContainerBinding.radioAll.setOnClickListener(this);
        mContainerBinding.radioAll.performClick();

        mContainerBinding.radioGp.setOnClickListener(this);

        mContainerBinding.radioFr.setOnClickListener(this);

        mContainerBinding.radioRf.setOnClickListener(this);

        mContainerBinding.radioHc.setOnClickListener(this);

        mContainerBinding.radioOt.setOnClickListener(this);
    }

    private void setAdapterItems() {
        ArrayAdapter<String> adapterItemsMonth = new ArrayAdapter<String>(this, R.layout.dropdown_item, Constants.ITEMS_MONTH);
        ArrayAdapter<String> adapterItemsContinent = new ArrayAdapter<String>(this, R.layout.dropdown_item, Constants.ITEMS_CONTINENT);

        mContainerBinding.autoCompleteMonth.setAdapter(adapterItemsMonth);
        mContainerBinding.autoCompleteContinent.setAdapter(adapterItemsContinent);

        mContainerBinding.autoCompleteMonth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                month = adapterView.getItemAtPosition(i).toString();
                setDataForRecyclerView(month, continent, radioItem);
            }
        });

        mContainerBinding.autoCompleteContinent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                continent = adapterView.getItemAtPosition(i).toString();
                setDataForRecyclerView(month, continent, radioItem);
            }
        });
    }

    public void setDataForRecyclerView(String m, String c, String r) {
        if (!m.isEmpty() && !c.isEmpty()) {
            priceListFclAdapter.setDataFcl(prepareDataForRecyclerView(m, c, r));
            mContainerBinding.priceListRcv.setAdapter(priceListFclAdapter);
            mContainerBinding.priceListRcv.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    public List<Fcl> prepareDataForRecyclerView(String m, String c, String r) {
        // reset a list when user choose different
        List<Fcl> subList = new ArrayList<>();
        try {
            for (Fcl f : listPriceList) {
                if (r.equalsIgnoreCase("all")) {
                    if (f.getMonth().equalsIgnoreCase(m) && f.getContinent().equalsIgnoreCase(c)) {
                        subList.add(f);
                    }
                } else {
                    if (f.getMonth().equalsIgnoreCase(m) && f.getContinent().equalsIgnoreCase(c)
                            && f.getType().equalsIgnoreCase(r)) {
                        subList.add(f);
                    }
                }
            }
        }catch (NullPointerException nullPointerException){
            Toast.makeText(this, nullPointerException.toString(), Toast.LENGTH_LONG).show();
        }
        return subList;
    }

    public List<Fcl> prepareDataForResume(String m, String c, String r, List<Fcl> list) {
        // reset a list when user choose different
        List<Fcl> subList = new ArrayList<>();
        try {
            for (Fcl f : list) {
                if (r.equalsIgnoreCase("all")) {
                    if (f.getMonth().equalsIgnoreCase(m) && f.getContinent().equalsIgnoreCase(c)) {
                        subList.add(f);
                    }
                } else {
                    if (f.getMonth().equalsIgnoreCase(m) && f.getContinent().equalsIgnoreCase(c)
                            && f.getType().equalsIgnoreCase(r)) {
                        subList.add(f);
                    }
                }
            }

        } catch (NullPointerException nullPointerException) {
            Toast.makeText(this, nullPointerException.toString(), Toast.LENGTH_LONG).show();
        }

        return subList;

    }

    private void getAllData() {
        this.listPriceList = new ArrayList<>();

        mFclViewModel.getFclList().observe(this, detailsPojoFcl -> {
            this.listPriceList = detailsPojoFcl;
        });
    }


    @Override
    protected void onResume() {
        super.onResume();

        priceListFclAdapter = new PriceListFclAdapter(this);
        mFclViewModel.getFclList().observe(this, detailsPojoFcl -> {
            priceListFclAdapter.setDataFcl(prepareDataForResume(month, continent, radioItem, detailsPojoFcl));
        });

        mContainerBinding.priceListRcv.setAdapter(priceListFclAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_fcl_fab:
                DialogFragment dialogFragment = InsertFclDialog.insertDialog();
                dialogFragment.show(getSupportFragmentManager(), "Insert Dialog");
                break;
            case R.id.radio_all:
                radioItem = mContainerBinding.radioAll.getText().toString();
                setDataForRecyclerView(month, continent, radioItem);
                break;

            case R.id.radio_gp:
                radioItem = mContainerBinding.radioGp.getText().toString();
                setDataForRecyclerView(month, continent, radioItem);
                break;

            case R.id.radio_fr:
                radioItem = mContainerBinding.radioFr.getText().toString();
                setDataForRecyclerView(month, continent, radioItem);
                break;

            case R.id.radio_rf:
                radioItem = mContainerBinding.radioRf.getText().toString();
                setDataForRecyclerView(month, continent, radioItem);
                break;

            case R.id.radio_hc:
                radioItem = mContainerBinding.radioHc.getText().toString();
                setDataForRecyclerView(month, continent, radioItem);
                break;

            case R.id.radio_ot:
                radioItem = mContainerBinding.radioOt.getText().toString();
                setDataForRecyclerView(month, continent, radioItem);
                break;
        }
    }
}