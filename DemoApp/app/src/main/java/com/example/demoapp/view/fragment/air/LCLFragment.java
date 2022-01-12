package com.example.demoapp.view.fragment.air;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.demoapp.R;
import com.example.demoapp.adapter.PriceListAIRAdapter;
import com.example.demoapp.databinding.FragmentLclBinding;
import com.example.demoapp.model.AirExport;
import com.example.demoapp.utilities.Constants;
import com.example.demoapp.view.dialog.air.ImportAndExportFragment;
import com.example.demoapp.viewmodel.AirExportViewModel;
import com.example.demoapp.viewmodel.CommunicateViewModel;

import java.util.ArrayList;
import java.util.List;

public class LCLFragment extends Fragment implements View.OnClickListener {
    FragmentLclBinding lclBinding;

    private String month = "";
    private String continent = "";
    PriceListAIRAdapter priceListAdapter;

    private AirExportViewModel mAirViewModel;

    private List<AirExport> airList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        lclBinding = FragmentLclBinding.inflate(inflater, container, false);
        View view = lclBinding.getRoot();

        priceListAdapter = new PriceListAIRAdapter(getContext());
        mAirViewModel = new ViewModelProvider(this).get(AirExportViewModel.class);

        CommunicateViewModel mCommunicateViewModel = new ViewModelProvider(getActivity()).get(CommunicateViewModel.class);

        mCommunicateViewModel.needReloading.observe(getViewLifecycleOwner(), needLoading -> {
            if (needLoading) {
                Log.d("onresume", String.valueOf(needLoading.toString()));
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
        ArrayAdapter<String> adapterItemsMonth = new ArrayAdapter<String>(getContext(), R.layout.dropdown_item, Constants.ITEMS_MONTH);
        ArrayAdapter<String> adapterItemsContinent = new ArrayAdapter<String>(getContext(), R.layout.dropdown_item, Constants.ITEMS_CONTINENT);

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
     */
    public void setDataForRecyclerView(String m, String c) {
        if (!m.isEmpty() && !c.isEmpty()) {

            priceListAdapter.setDataAir(prepareDataForRecyclerView(m, c));
            lclBinding.priceListRcv.setAdapter(priceListAdapter);
            lclBinding.priceListRcv.setLayoutManager(new LinearLayoutManager(getContext()));

        }
    }

    private List<AirExport> prepareDataForRecyclerView(String m, String c) {
        List<AirExport> list = new ArrayList<>();
        try {
            for (AirExport a : airList) {
                if (a.getMonth().equalsIgnoreCase(m) && a.getContinent().equalsIgnoreCase(c)) {
                    list.add(a);
                }
            }
        } catch (NullPointerException nullPointerException) {
            Toast.makeText(getContext(), nullPointerException.toString(), Toast.LENGTH_LONG).show();
        }
        return list;
    }

    public List<AirExport> prepareDataForResume(String m, String c, List<AirExport> list) {
        // reset a list when user choose different
        List<AirExport> subList = new ArrayList<>();
        try {
            for (AirExport air : list) {
                if (air.getMonth().equalsIgnoreCase(m) && air.getContinent().equalsIgnoreCase(c)) {
                    subList.add(air);
                }
            }
        }catch (NullPointerException nullPointerException){
            Toast.makeText(getContext(), nullPointerException.toString(), Toast.LENGTH_LONG).show();
        }

        return subList;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

        mAirViewModel.getLclList().observe(getViewLifecycleOwner(), airs -> {
            priceListAdapter.setDataAir(prepareDataForResume(month, continent, airs));
        });
        lclBinding.priceListRcv.setAdapter(priceListAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_air_fab:
                DialogFragment dialogFragment = ImportAndExportFragment.insertImportAndExportDiaLogAIR();
                dialogFragment.show(getParentFragmentManager(), "Insert Dialog");

                break;
        }
    }

}
