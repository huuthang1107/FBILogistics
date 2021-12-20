package com.example.demoapp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

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
import com.example.demoapp.services.AIRService;
import com.example.demoapp.utilities.Contants;
import com.example.demoapp.view.dialog.InsertAirDialog;
import com.example.demoapp.viewmodel.AirViewModel;
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
    private final String[] itemsMonth = {"Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7",
            "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"};

    private final String[] itemsContinent = {"Asia", "Europe", "America", "Africa", "Australia"};

    private ArrayAdapter<String> adapterItemsMonth, adapterItemsContinent;
    private String month = "";
    private String continent = "";
    PriceListAIRAdapter priceListAdapter;

    private AirViewModel mAirViewModel;

    private  List<Air> airList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        lclBinding = FragmentLclBinding.inflate(inflater, container, false);
        View view = lclBinding.getRoot();

        mAirViewModel = new ViewModelProvider(this).get(AirViewModel.class);
        priceListAdapter = new PriceListAIRAdapter(getContext());

        mAirViewModel.getFclList().observe(getViewLifecycleOwner(), air -> {
            priceListAdapter.setDataAir(air);
        });

        getDataAIR();
        setAdapterItems();
        setUpButtons();


        return view;
    }

    private void getDataAIR() {
        airList = new ArrayList<>();
        mAirViewModel.getFclList().observe(getViewLifecycleOwner(), detailsPojoAir -> {
            this.airList = detailsPojoAir;
        });
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Contants.URL_API)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        AIRService airService = retrofit.create(AIRService.class);
//        Call<List<Air>> call = airService.getpriceAIR();
//
//        call.enqueue(new Callback<List<Air>>() {
//            @Override
//            public void onResponse(Call<List<Air>> call, Response<List<Air>> response) {
//                List<Air> list = response.body();
//
//                for(int i=0; i<list.size(); i++){
//                    airList.add(new Air(list.get(i).getStt(), list.get(i).getAol(), list.get(i).getAod(),
//                            list.get(i).getDim(), list.get(i).getGrossweight(),list.get(i).getTypeofcargo(),
//                            list.get(i).getAirfreight(), list.get(i).getSurcharge(),
//                            list.get(i).getAirlines(),list.get(i).getSchedule(), list.get(i).getTransittime(),
//                            list.get(i).getValid(), list.get(i).getNote(), list.get(i).getMonth(), list.get(i).getContinent()));
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Air>> call, Throwable t) {
//
//            }
//        });

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
