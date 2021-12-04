package com.example.demoapp.fragment_pricelist;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demoapp.R;
import com.example.demoapp.db.DetailsPojo;
import com.example.demoapp.db.FragmentDialogInsert;
import com.example.demoapp.db.GetAPI;
import com.example.demoapp.db.PriceListModel;
import com.example.demoapp.db.recyclerview.PriceListAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PriceListAsia extends Fragment implements View.OnClickListener {

    private RadioButton radioGP, radioFR, radioRF, radioOT, radioHC, radioAll;
    private TableRow tableRow1;
    private Button btnAdd, btnDel;

    // Recycler View
    RecyclerView rcvPriceAsia;
    PriceListAdapter priceListAdapter;

    // prepare data for recycler view
    List<PriceListModel> listPriceList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pricelist_asia, container, false);
        
        radioGP = view.findViewById(R.id.radio_gp);
        radioFR = view.findViewById(R.id.radio_fr);
        radioRF = view.findViewById(R.id.radio_rf);
        radioOT = view.findViewById(R.id.radio_ot);
        radioHC = view.findViewById(R.id.radio_hc);
        radioAll = view.findViewById(R.id.radio_all);
        radioAll.setChecked(true);

        // table row 1
        tableRow1 = view.findViewById(R.id.table_price_row1);

        //Recycler View

        rcvPriceAsia = view.findViewById(R.id.price_list_asia_rcv);
        // setRecyclerView();

        // listener click add
        btnAdd = view.findViewById(R.id.btn_add_row);

        radioGP.setOnClickListener(this);
        radioFR.setOnClickListener(this);
        radioRF.setOnClickListener(this);
        radioOT.setOnClickListener(this);
        radioHC.setOnClickListener(this);
        radioAll.setOnClickListener(this);
        radioAll.performClick();

        // add to table
        btnAdd.setOnClickListener(this);

        return view;
    }

    // change column name when click radio button
    public void changeTableName(String str) {

        TextView view1 = (TextView) tableRow1.getChildAt(3);
        TextView view2 = (TextView) tableRow1.getChildAt(4);
        TextView view3 = (TextView) tableRow1.getChildAt(5);
        TextView view4 = (TextView) tableRow1.getChildAt(6);

        view1.setText(getString(R.string.col_of2).concat(str));
        view2.setText(getString(R.string.col_of4).concat(str));
        view3.setText(getString(R.string.col_su2).concat(str));
        view4.setText(getString(R.string.col_su4).concat(str));
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        // which radio button is clicked
        switch (view.getId()) {
            case R.id.radio_all:
                changeTableName("");
                listPriceList = new ArrayList<>();
                process();
                break;
            case R.id.radio_gp:
                changeTableName("GP");
                listPriceList = new ArrayList<>();
                processGp();
                break;
            case R.id.radio_fr:
                changeTableName("FR");
                listPriceList = new ArrayList<>();
                processFr();
                break;
            case R.id.radio_rf:
                changeTableName("RF");
                listPriceList = new ArrayList<>();
                processRf();
                break;
            case R.id.radio_ot:
                changeTableName("OT");
                listPriceList = new ArrayList<>();
                processOt();
                break;
            case R.id.radio_hc:
                changeTableName("HC");
                listPriceList = new ArrayList<>();
                processHc();
                break;
            case R.id.btn_add_row:
                DialogFragment dialogFragment = FragmentDialogInsert.insertDialog();
                dialogFragment.show(getParentFragmentManager(), "Insert Dialog");
        }
    }

    // Get all data from db to show into table
    public void process() {

        // Using retrofit library
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GetAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //initialize api class
        GetAPI getAPI = retrofit.create(GetAPI.class);

        // Fetching the values into Pojo File
        Call<List<DetailsPojo>> call = getAPI.getStatus();

        // call
        call.enqueue(new Callback<List<DetailsPojo>>() {
            @Override
            public void onResponse(@NonNull Call<List<DetailsPojo>> call, @NonNull Response<List<DetailsPojo>> response) {
                List<DetailsPojo> priceListData = response.body();
                for (int i = 0; i < priceListData.size(); i++) {
                    listPriceList.add(new PriceListModel(priceListData.get(i).getStt(), priceListData.get(i).getPol(),
                            priceListData.get(i).getPod(), priceListData.get(i).getOf20(),
                            priceListData.get(i).getOf40(), priceListData.get(i).getSu20(),
                            priceListData.get(i).getSu40(), priceListData.get(i).getLinelist(),
                            priceListData.get(i).getNotes(), priceListData.get(i).getValid(),
                            priceListData.get(i).getNotes2()));
                }
                rcvPriceAsia.setHasFixedSize(true);
                rcvPriceAsia.setLayoutManager(new LinearLayoutManager(getContext()));
                priceListAdapter = new PriceListAdapter(getContext(), listPriceList);
                rcvPriceAsia.setAdapter(priceListAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<DetailsPojo>> call, @NonNull Throwable t) {

            }
        });
    }

    // Get data of GP type to show into table
    public void processGp() {
        // Using retrofit library
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GetAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //initialize api class
        GetAPI getAPI = retrofit.create(GetAPI.class);

        // Fetching the values into Pojo File
        Call<List<DetailsPojo>> call = getAPI.getStatus();

        // call
        call.enqueue(new Callback<List<DetailsPojo>>() {
            @Override
            public void onResponse(@NonNull Call<List<DetailsPojo>> call, @NonNull Response<List<DetailsPojo>> response) {
                List<DetailsPojo> priceListData = response.body();
                for (int i = 0; i < priceListData.size(); i++) {
                    if ("gp".equalsIgnoreCase(priceListData.get(i).getType())) {
                        listPriceList.add(new PriceListModel(priceListData.get(i).getStt(), priceListData.get(i).getPol(),
                                priceListData.get(i).getPod(), priceListData.get(i).getOf20(),
                                priceListData.get(i).getOf40(), priceListData.get(i).getSu20(),
                                priceListData.get(i).getSu40(), priceListData.get(i).getLinelist(),
                                priceListData.get(i).getNotes(), priceListData.get(i).getValid(),
                                priceListData.get(i).getNotes2()));
                    }
                }
                rcvPriceAsia.setHasFixedSize(true);
                rcvPriceAsia.setLayoutManager(new LinearLayoutManager(getContext()));
                priceListAdapter = new PriceListAdapter(getContext(), listPriceList);
                rcvPriceAsia.setAdapter(priceListAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<DetailsPojo>> call, @NonNull Throwable t) {

            }
        });
    }

    // Get data of FR type to show into table
    public void processFr() {

        // Using retrofit library
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GetAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //initialize api class
        GetAPI getAPI = retrofit.create(GetAPI.class);

        // Fetching the values into Pojo File
        Call<List<DetailsPojo>> call = getAPI.getStatus();

        // call
        call.enqueue(new Callback<List<DetailsPojo>>() {
            @Override
            public void onResponse(@NonNull Call<List<DetailsPojo>> call, @NonNull Response<List<DetailsPojo>> response) {
                List<DetailsPojo> priceListData = response.body();
                for (int i = 0; i < priceListData.size(); i++) {
                    if ("fr".equalsIgnoreCase(priceListData.get(i).getType())) {
                        listPriceList.add(new PriceListModel(priceListData.get(i).getStt(), priceListData.get(i).getPol(),
                                priceListData.get(i).getPod(), priceListData.get(i).getOf20(),
                                priceListData.get(i).getOf40(), priceListData.get(i).getSu20(),
                                priceListData.get(i).getSu40(), priceListData.get(i).getLinelist(),
                                priceListData.get(i).getNotes(), priceListData.get(i).getValid(),
                                priceListData.get(i).getNotes2()));
                    }
                }
                rcvPriceAsia.setHasFixedSize(true);
                rcvPriceAsia.setLayoutManager(new LinearLayoutManager(getContext()));
                priceListAdapter = new PriceListAdapter(getContext(), listPriceList);
                rcvPriceAsia.setAdapter(priceListAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<DetailsPojo>> call, @NonNull Throwable t) {

            }
        });
    }

    // Get data of RF type to show into table
    public void processRf() {

        // Using retrofit library
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GetAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //initialize api class
        GetAPI getAPI = retrofit.create(GetAPI.class);

        // Fetching the values into Pojo File
        Call<List<DetailsPojo>> call = getAPI.getStatus();

        // call
        call.enqueue(new Callback<List<DetailsPojo>>() {
            @Override
            public void onResponse(@NonNull Call<List<DetailsPojo>> call, @NonNull Response<List<DetailsPojo>> response) {
                List<DetailsPojo> priceListData = response.body();
                for (int i = 0; i < priceListData.size(); i++) {
                    if ("rf".equalsIgnoreCase(priceListData.get(i).getType())) {
                        listPriceList.add(new PriceListModel(priceListData.get(i).getStt(), priceListData.get(i).getPol(),
                                priceListData.get(i).getPod(), priceListData.get(i).getOf20(),
                                priceListData.get(i).getOf40(), priceListData.get(i).getSu20(),
                                priceListData.get(i).getSu40(), priceListData.get(i).getLinelist(),
                                priceListData.get(i).getNotes(), priceListData.get(i).getValid(),
                                priceListData.get(i).getNotes2()));
                    }
                }
                rcvPriceAsia.setHasFixedSize(true);
                rcvPriceAsia.setLayoutManager(new LinearLayoutManager(getContext()));
                priceListAdapter = new PriceListAdapter(getContext(), listPriceList);
                rcvPriceAsia.setAdapter(priceListAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<DetailsPojo>> call, @NonNull Throwable t) {

            }
        });
    }

    // Get data of OT type to show into table
    public void processOt() {

        // Using retrofit library
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GetAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //initialize api class
        GetAPI getAPI = retrofit.create(GetAPI.class);

        // Fetching the values into Pojo File
        Call<List<DetailsPojo>> call = getAPI.getStatus();

        // call
        call.enqueue(new Callback<List<DetailsPojo>>() {
            @Override
            public void onResponse(@NonNull Call<List<DetailsPojo>> call, @NonNull Response<List<DetailsPojo>> response) {
                List<DetailsPojo> priceListData = response.body();
                for (int i = 0; i < priceListData.size(); i++) {
                    if ("ot".equalsIgnoreCase(priceListData.get(i).getType())) {
                        listPriceList.add(new PriceListModel(priceListData.get(i).getStt(), priceListData.get(i).getPol(),
                                priceListData.get(i).getPod(), priceListData.get(i).getOf20(),
                                priceListData.get(i).getOf40(), priceListData.get(i).getSu20(),
                                priceListData.get(i).getSu40(), priceListData.get(i).getLinelist(),
                                priceListData.get(i).getNotes(), priceListData.get(i).getValid(),
                                priceListData.get(i).getNotes2()));
                    }
                }
                rcvPriceAsia.setHasFixedSize(true);
                rcvPriceAsia.setLayoutManager(new LinearLayoutManager(getContext()));
                priceListAdapter = new PriceListAdapter(getContext(), listPriceList);
                rcvPriceAsia.setAdapter(priceListAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<DetailsPojo>> call, @NonNull Throwable t) {

            }
        });
    }

    // Get data of HC type to show into table
    public void processHc() {

        // Using retrofit library
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GetAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //initialize api class
        GetAPI getAPI = retrofit.create(GetAPI.class);

        // Fetching the values into Pojo File
        Call<List<DetailsPojo>> call = getAPI.getStatus();

        // call
        call.enqueue(new Callback<List<DetailsPojo>>() {
            @Override
            public void onResponse(@NonNull Call<List<DetailsPojo>> call, @NonNull Response<List<DetailsPojo>> response) {
                List<DetailsPojo> priceListData = response.body();
                for (int i = 0; i < priceListData.size(); i++) {
                    if ("hc".equalsIgnoreCase(priceListData.get(i).getType())) {
                        listPriceList.add(new PriceListModel(priceListData.get(i).getStt(), priceListData.get(i).getPol(),
                                priceListData.get(i).getPod(), priceListData.get(i).getOf20(),
                                priceListData.get(i).getOf40(), priceListData.get(i).getSu20(),
                                priceListData.get(i).getSu40(), priceListData.get(i).getLinelist(),
                                priceListData.get(i).getNotes(), priceListData.get(i).getValid(),
                                priceListData.get(i).getNotes2()));
                    }
                }
                rcvPriceAsia.setHasFixedSize(true);
                rcvPriceAsia.setLayoutManager(new LinearLayoutManager(getContext()));
                priceListAdapter = new PriceListAdapter(getContext(), listPriceList);
                rcvPriceAsia.setAdapter(priceListAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<DetailsPojo>> call, @NonNull Throwable t) {

            }
        });
    }

}
