package com.example.demoapp.view.fragment;

import android.annotation.SuppressLint;
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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.demoapp.R;
import com.example.demoapp.adapter.PriceListAdapterAIR;
import com.example.demoapp.api.GetAIR;
import com.example.demoapp.databinding.FragmentLclBinding;
import com.example.demoapp.model.Air;
import com.example.demoapp.view.dialog.InsertAirDialog;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LCLFragment extends Fragment implements View.OnClickListener{

    private FragmentLclBinding lclBinding;
    private PriceListAdapterAIR priceListAdapterAIR;

    private final String[] itemsMonth = {"Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7",
            "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"};

    private final String[] itemsContinent = {"Asia", "Europe", "America", "Africa", "Australia"};


    private ArrayAdapter<String> adapterItemsMonth, adapterItemsContinent;
    private List<Air> list = new ArrayList<>();

    private String month, continent;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        lclBinding = FragmentLclBinding.inflate(inflater, container, false);
        View view = lclBinding.getRoot();

        setAdapterItems();
        setUpButtons();


        return view;
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
            }
        });

        lclBinding.autoCompleteContinent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                continent = adapterView.getItemAtPosition(i).toString();
            }
        });

    }



    public void setUpButtons() {

        lclBinding.fragmentLclFab.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_lcl_fab:
                DialogFragment dialogFragment = InsertAirDialog.insertDiaLogAIR();
                dialogFragment.show(getParentFragmentManager(), "Insert Dialog");
                break;


        }
    }
    public void process() {


        // Using retrofit library
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GetAIR.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //initialize api class
        GetAIR getAPI = retrofit.create(GetAIR.class);

        // Fetching the values into Pojo File
        Call<List<Air>> call = getAPI.getpricelistAIR();

        call.enqueue(new Callback<List<Air>>() {
            @Override
            public void onResponse(Call<List<Air>> call, Response<List<Air>> response) {
                List<Air> airList = response.body();
                for(int i=0 ; i<airList.size(); i++){
                    list.add(new Air(airList.get(i).getStt(), airList.get(i).getAol(),
                            airList.get(i).getAod(), airList.get(i).getDim(),
                            airList.get(i).getGross(),airList.get(i).getTypeOfCargo(),
                            airList.get(i).getAirFreight(), airList.get(i).getSurcharge(),
                            airList.get(i).getAirLines(), airList.get(i).getSchedule(),
                            airList.get(i).getTransitTime(), airList.get(i).getValid(),
                            airList.get(i).getNote(),airList.get(i).getMonth(), airList.get(i).getContinent()));
                }
                lclBinding.priceListAsiaRcv.setHasFixedSize(true);
                lclBinding.priceListAsiaRcv.setLayoutManager(new LinearLayoutManager(getContext()));
                priceListAdapterAIR = new PriceListAdapterAIR(getContext(),list);
                lclBinding.priceListAsiaRcv.setAdapter(priceListAdapterAIR);
            }

            @Override
            public void onFailure(@NonNull Call<List<Air>> call, @NonNull Throwable t) {

            }
        });

    }
}
