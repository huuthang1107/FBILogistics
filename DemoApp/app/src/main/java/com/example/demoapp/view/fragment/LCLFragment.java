package com.example.demoapp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.demoapp.R;
import com.example.demoapp.adapter.PriceListAIRAdapter;
import com.example.demoapp.adapter.PriceListAdapter;
import com.example.demoapp.databinding.FragmentLclBinding;
import com.example.demoapp.model.Air;
import com.example.demoapp.model.Fcl;
import com.example.demoapp.view.dialog.InsertAirDialog;
import com.example.demoapp.viewmodel.AirViewModel;
import com.example.demoapp.viewmodel.CommunicateViewModel;

import java.util.ArrayList;
import java.util.List;

public class LCLFragment extends Fragment implements View.OnClickListener {
    FragmentLclBinding lclBinding;
    private final String[] itemsMonth = {"Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7",
            "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"};

    private final String[] itemsContinent = {"Asia", "Europe", "America", "Africa", "Australia"};

    private ArrayAdapter<String> adapterItemsMonth, adapterItemsContinent;
    private String month = "";
    private String continent = "";
    PriceListAIRAdapter priceListAdapter;

    private AirViewModel mAirViewModel;

    private CommunicateViewModel mCommunicateViewModel;

    private List<Air> airList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        lclBinding = FragmentLclBinding.inflate(inflater, container, false);
        View view = lclBinding.getRoot();

        mAirViewModel = new ViewModelProvider(this).get(AirViewModel.class);
        priceListAdapter = new PriceListAIRAdapter(getContext());

        mCommunicateViewModel = new ViewModelProvider(getActivity()).get(CommunicateViewModel.class);
        mCommunicateViewModel.needReloading.observe(getViewLifecycleOwner(), needLoading ->{
            if(needLoading){
                onResume();
            }
        });


        getDataAIR();
        setAdapterItems();
        setUpButtons();


        return view;
    }

    private void getDataAIR() {
        airList = new ArrayList<>();
        mAirViewModel.getLclList().observe(getViewLifecycleOwner(), detailsPojoAir -> {
            this.airList = detailsPojoAir;
        });


    }

    private void setUpButtons() {
        lclBinding.fragmentAirFab.setOnClickListener(this);
    }

    public void setAdapterItems() {
        adapterItemsMonth = new ArrayAdapter<String>(getContext(), R.layout.dropdown_item, itemsMonth);
        adapterItemsContinent = new ArrayAdapter<String>(getContext(), R.layout.dropdown_item, itemsContinent);

        lclBinding.autoCompleteMonth.setAdapter(adapterItemsMonth);
        lclBinding.autoCompleteContinent.setAdapter(adapterItemsContinent);

        lclBinding.autoCompleteMonth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                month = adapterView.getItemAtPosition(i).toString();
                setDataForRecyclerView(month, continent);
            }
        });

        lclBinding.autoCompleteContinent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                continent = adapterView.getItemAtPosition(i).toString();
                setDataForRecyclerView(month, continent);
            }
        });

    }


    /**
     * this method will set data for recycler view
     *
     * @param m month
     * @param c continent
     *
     */
    public void setDataForRecyclerView(String m, String c) {
        if (!m.isEmpty() && !c.isEmpty()) {
            priceListAdapter.setDataAir(prepareDataForRecyclerView(m,c));
            lclBinding.priceListRcv.setAdapter(priceListAdapter);

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
    public List<Air> prepareDataForResume(String m, String c, List<Air> list) {
        // reset a list when user choose different
        List<Air> subList = new ArrayList<>();
        for (Air air : list) {
                if (air.getMonth().equalsIgnoreCase(m) && air.getContinent().equalsIgnoreCase(c)) {
                    subList.add(air);
                }
        }
        return subList;
    }

    @Override
    public void onResume() {
        super.onResume();
        priceListAdapter = new PriceListAIRAdapter(getContext());
        mAirViewModel.getLclList().observe(getViewLifecycleOwner(), detailsPojoAir -> {
            priceListAdapter.setDataAir( prepareDataForResume(month, continent, detailsPojoAir));
        });

        lclBinding.priceListRcv.setAdapter(priceListAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_air_fab:
                DialogFragment dialogFragment = InsertAirDialog.insertDiaLogAIR();
                dialogFragment.show(getParentFragmentManager(), "Insert Dialog");

                break;
        }
    }

}
