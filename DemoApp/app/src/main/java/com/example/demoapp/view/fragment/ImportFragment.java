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
import com.example.demoapp.adapter.PriceListImportAdapter;
import com.example.demoapp.services.FCLService;
import com.example.demoapp.databinding.FragmentImportBinding;
import com.example.demoapp.model.DetailsPojoImport;
import com.example.demoapp.model.Import;
import com.example.demoapp.services.ImportService;
import com.example.demoapp.view.dialog.InsertImportDialog;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImportFragment extends Fragment implements View.OnClickListener {

    FragmentImportBinding binding;
    private final String[] itemsMonth = {"Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7",
            "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"};

    private final String[] itemsContinent = {"Asia", "Europe", "America", "Africa", "Australia"};

    private ArrayAdapter<String> adapterItemsMonth, adapterItemsContinent;

    private String month = "";
    private String continent = "";
    private String radioItem = "All";

    List<Import> listPriceList = new ArrayList<>();
    PriceListImportAdapter priceListAdapter;

    /**
     * this method will create a view (fragment)
     *
     * @param inflater           fragment
     * @param container          container
     * @param savedInstanceState save
     * @return view
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentImportBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        setAdapterItems();
        setUpButtons();
        getAllData();

        return view;
    }

    /**
     * this method will listen a event of auto complete (month, continent)
     */
    public void setAdapterItems() {
        adapterItemsMonth = new ArrayAdapter<String>(getContext(), R.layout.dropdown_item, itemsMonth);
        adapterItemsContinent = new ArrayAdapter<String>(getContext(), R.layout.dropdown_item, itemsContinent);

        binding.autoCompleteMonth.setAdapter(adapterItemsMonth);
        binding.autoCompleteContinent.setAdapter(adapterItemsContinent);

        binding.autoCompleteMonth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                month = adapterView.getItemAtPosition(i).toString();
                setDataForRecyclerView(month, continent, radioItem);
            }
        });

        binding.autoCompleteContinent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
            binding.priceListRcv.setHasFixedSize(true);

            binding.priceListRcv.setLayoutManager(new LinearLayoutManager(getContext()));

            priceListAdapter = new PriceListImportAdapter(getContext(), prepareDataForRecyclerView(m, c, r));

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

    /**
     * this method will get all data from database
     */
    public void getAllData() {
        // Using retrofit library
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FCLService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //initialize api class
        ImportService importService = retrofit.create(ImportService.class);

        // Fetching the values into Pojo File
        Call<List<DetailsPojoImport>> call = importService.getStatusImport();

        // call
        call.enqueue(new Callback<List<DetailsPojoImport>>() {
            @Override
            public void onResponse(@NonNull Call<List<DetailsPojoImport>> call,
                                   @NonNull Response<List<DetailsPojoImport>> response) {
                List<DetailsPojoImport> priceListData = response.body();
                for (int i = 0; i < priceListData.size(); i++) {
                    listPriceList.add(new Import(priceListData.get(i).getStt(),
                            priceListData.get(i).getPol(), priceListData.get(i).getPod(),
                            priceListData.get(i).getOf20(), priceListData.get(i).getOf40(),
                            priceListData.get(i).getSurcharge(), priceListData.get(i).getTotalFreight(),
                            priceListData.get(i).getCarrier(), priceListData.get(i).getSchedule(),
                            priceListData.get(i).getTransitTime(), priceListData.get(i).getFreeTime(),
                            priceListData.get(i).getValid(), priceListData.get(i).getNote(),
                            priceListData.get(i).getType(), priceListData.get(i).getMonth(),
                            priceListData.get(i).getContinent()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<DetailsPojoImport>> call, @NonNull Throwable t) {

            }
        });

    }

    /**
     * this method will set listen for buttons
     */
    public void setUpButtons() {
        binding.fragmentFclFab.setOnClickListener(this);

        binding.radioAll.setOnClickListener(this);
        binding.radioAll.performClick();

        binding.radioGp.setOnClickListener(this);

        binding.radioFr.setOnClickListener(this);

        binding.radioRf.setOnClickListener(this);

        binding.radioHq.setOnClickListener(this);

        binding.radioOt.setOnClickListener(this);

        binding.radioTk.setOnClickListener(this);
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
                dialogFragment.show(getParentFragmentManager(), "Insert Dialog");
                break;
            case R.id.radio_all:
                radioItem = binding.radioAll.getText().toString();
                setDataForRecyclerView(month, continent, radioItem);
                break;

            case R.id.radio_gp:
                radioItem = binding.radioGp.getText().toString();
                setDataForRecyclerView(month, continent, radioItem);
                break;

            case R.id.radio_fr:
                radioItem = binding.radioFr.getText().toString();
                setDataForRecyclerView(month, continent, radioItem);
                break;

            case R.id.radio_rf:
                radioItem = binding.radioRf.getText().toString();
                setDataForRecyclerView(month, continent, radioItem);
                break;

            case R.id.radio_hq:
                radioItem = binding.radioHq.getText().toString();
                setDataForRecyclerView(month, continent, radioItem);
                break;

            case R.id.radio_ot:
                radioItem = binding.radioOt.getText().toString();
                setDataForRecyclerView(month, continent, radioItem);
                break;
            case R.id.radio_tk:
                radioItem = binding.radioTk.getText().toString();
                setDataForRecyclerView(month, continent, radioItem);
                break;
        }
    }
}
