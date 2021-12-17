package com.example.demoapp.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
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
import com.example.demoapp.adapter.PriceListAdapter;
import com.example.demoapp.databinding.FragmentFclBinding;
import com.example.demoapp.model.Fcl;
import com.example.demoapp.view.dialog.InsertFclDialog;
import com.example.demoapp.viewmodel.FclViewModel;

import java.util.ArrayList;
import java.util.List;

public class FCLFragment extends Fragment implements View.OnClickListener {

    private FragmentFclBinding binding;

    private final String[] itemsMonth = {"Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7",
            "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"};

    private final String[] itemsContinent = {"Asia", "Europe", "America", "Africa", "Australia"};

    private ArrayAdapter<String> adapterItemsMonth, adapterItemsContinent;

    private String month = "";
    private String continent = "";
    private String radioItem = "All";

    private List<Fcl> listPriceList;
    private PriceListAdapter priceListAdapter;

    private FclViewModel mFclViewModel;

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
        binding = FragmentFclBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        setAdapterItems();
        setUpButtons();
        getAllData();

        return view;
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        Toast.makeText(getContext(), "Resume", Toast.LENGTH_LONG).show();
//    }

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
            mFclViewModel = new ViewModelProvider(this).get(FclViewModel.class);
            priceListAdapter = new PriceListAdapter(getContext());
            priceListAdapter.setDataFcl(prepareDataForRecyclerView(m, c, r));
            binding.priceListRcv.setAdapter(priceListAdapter);
            binding.priceListRcv.setLayoutManager(new LinearLayoutManager(getContext()));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(getContext(), "Resume", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * this method will filter list data by month and continent
     *
     * @param m month
     * @param c continent
     * @return get list by month and continent
     */
    public List<Fcl> prepareDataForRecyclerView(String m, String c, String r) {
        // reset a list when user choose different
        List<Fcl> subList = new ArrayList<>();
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
        return subList;
    }

    /**
     * this method will get all data from database
     */
    public void getAllData() {
        this.listPriceList = new ArrayList<>();

        mFclViewModel.getFclList().observe(getViewLifecycleOwner(), detailsPojoFcl -> {
            this.listPriceList = detailsPojoFcl;
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

        binding.radioHc.setOnClickListener(this);

        binding.radioOt.setOnClickListener(this);
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
                DialogFragment dialogFragment = InsertFclDialog.insertDialog();
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

            case R.id.radio_hc:
                radioItem = binding.radioHc.getText().toString();
                setDataForRecyclerView(month, continent, radioItem);
                break;

            case R.id.radio_ot:
                radioItem = binding.radioOt.getText().toString();
                setDataForRecyclerView(month, continent, radioItem);
                break;
        }
    }
}
