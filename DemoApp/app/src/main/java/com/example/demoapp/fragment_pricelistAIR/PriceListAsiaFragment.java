package com.example.demoapp.fragment_pricelistAIR;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.demoapp.R;
import com.example.demoapp.adapter.PriceListViewAdapterLCL;
import com.example.demoapp.db.GetAPI;
import com.example.demoapp.db.DetailsAIR;
import com.example.demoapp.db.recyclerview.PriceListAIRAdapter;
import com.example.demoapp.model.PriceListAIR;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PriceListAsiaFragment extends Fragment implements View.OnClickListener {

    private TableRow tableRow;
    private Button btnAdd, btnDel;

    private RecyclerView rcvRecyclerViewAsia;
    PriceListAIRAdapter priceListViewAdapterLCL;

    List<PriceListAIR> listPriceList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_price_list_asia, container, false);

        tableRow = view.findViewById(R.id.table_price_row1);
        rcvRecyclerViewAsia = view.findViewById(R.id.price_list_asia_rcv);

        btnAdd = view.findViewById(R.id.btn_add_row);

        btnAdd.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_row:
                DialogFragment dialogFragment = DialogInsertFragment.insertDiaLogAIR();
                dialogFragment.show(getParentFragmentManager(),"Insert DiaLog AIR");
          

        }

//        process();
    }
    public void process() {

        // Using retrofit library
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GetAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //initialize api class
        GetAPI getAPI = retrofit.create(GetAPI.class);

        Call<List<DetailsAIR>> call = getAPI.getpriceAIR();

        call.enqueue(new Callback<List<DetailsAIR>>() {
            @Override
            public void onResponse(Call<List<DetailsAIR>> call, Response<List<DetailsAIR>> response) {
                List<DetailsAIR> list = response.body();
                for(int i=0 ; i<list.size(); i++){
                    listPriceList.add(new PriceListAIR(list.get(i).getStt(), list.get(i).getAol(),
                            list.get(i).getAod(),list.get(i).getDim(), list.get(i).getGrossweight(),
                            list.get(i).getTypeofcargo(), list.get(i).getAirfreight(),
                            list.get(i).getSurcharge(), list.get(i).getAirlines(),
                            list.get(i).getSchedule(), list.get(i).getTransittime(),
                            list.get(i).getValid(), list.get(i).getNote()));
                }
                rcvRecyclerViewAsia.setHasFixedSize(true);
                rcvRecyclerViewAsia.setLayoutManager(new LinearLayoutManager(getContext()));
                priceListViewAdapterLCL = new PriceListAIRAdapter(getContext(), listPriceList);
                rcvRecyclerViewAsia.setAdapter(priceListViewAdapterLCL);

            }

            @Override
            public void onFailure(Call<List<DetailsAIR>> call, Throwable t) {
                Toast.makeText(getContext(),"Load Price AIR Error", Toast.LENGTH_SHORT).show();
            }
        });


    }
}