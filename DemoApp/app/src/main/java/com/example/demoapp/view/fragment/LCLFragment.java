package com.example.demoapp.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.demoapp.R;
import com.example.demoapp.adapter.PriceListAIRAdapter;
import com.example.demoapp.databinding.FragmentLclBinding;
import com.example.demoapp.model.Air;
import com.example.demoapp.model.DetailsAIR;
import com.example.demoapp.services.AIRService;
import com.example.demoapp.view.dialog.InsertAirDialog;
import com.example.demoapp.viewmodel.AIRViewModel;
import com.example.demoapp.viewmodel.FclViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LCLFragment extends Fragment implements View.OnClickListener {
    FragmentLclBinding lclBinding;
    AIRViewModel mAirViewModel;
    private final String[] itemsMonth = {"Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7",
            "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"};

    private final String[] itemsContinent = {"Asia", "Europe", "America", "Africa", "Australia"};

    private ArrayAdapter<String> adapterItemsMonth, adapterItemsContinent;
    private String month = "";
    private String continent = "";
    PriceListAIRAdapter priceListAdapter;
    private List<DetailsAIR> airList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        lclBinding = FragmentLclBinding.inflate(inflater, container, false);
        View view = lclBinding.getRoot();

        setAdapterItems();
        setUpButtons();
        getDataAIR();

        return view;
    }

    private void getDataAIR() {
        mAirViewModel = new ViewModelProvider(this).get(AIRViewModel.class);
        mAirViewModel.getAIRList().observe(getViewLifecycleOwner(), detailsAIRS -> {
            this.airList = detailsAIRS;
        });

    }

    private void setUpButtons() {
        lclBinding.fragmentFclFab.setOnClickListener(this);
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
            lclBinding.priceListRcv.setHasFixedSize(true);

            lclBinding.priceListRcv.setLayoutManager(new LinearLayoutManager(getContext()));

            priceListAdapter = new PriceListAIRAdapter(getContext(),prepareDataForRecyclerView(m,c));

            lclBinding.priceListRcv.setAdapter(priceListAdapter);
        }
    }

    private List<DetailsAIR> prepareDataForRecyclerView(String m, String c) {
        List<DetailsAIR> list = new ArrayList<>();

        for (DetailsAIR a : airList) {
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
                dialogFragment.show(getParentFragmentManager(), "Insert Dialog");

                break;
        }
    }
}
