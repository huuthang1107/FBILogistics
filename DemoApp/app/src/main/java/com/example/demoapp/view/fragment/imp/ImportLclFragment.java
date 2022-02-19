package com.example.demoapp.view.fragment.imp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.demoapp.R;
import com.example.demoapp.adapter.PriceListImportLclAdapter;
import com.example.demoapp.databinding.FragmentImportLclBinding;
import com.example.demoapp.model.ImportLcl;
import com.example.demoapp.utilities.Constants;
import com.example.demoapp.view.dialog.imp.InsertImportLclDialog;
import com.example.demoapp.viewmodel.CommunicateViewModel;
import com.example.demoapp.viewmodel.ImportLclViewModel;

import java.util.ArrayList;
import java.util.List;


public class ImportLclFragment extends Fragment implements View.OnClickListener {

    private FragmentImportLclBinding binding;


    private String month = "";
    private String continent = "";

    List<ImportLcl> listPriceList = new ArrayList<>();
    private PriceListImportLclAdapter priceListAdapter;
    private ImportLclViewModel mImportViewModel;

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
        binding = FragmentImportLclBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        priceListAdapter = new PriceListImportLclAdapter(getContext());
        mImportViewModel = new ViewModelProvider(this).get(ImportLclViewModel.class);

        CommunicateViewModel mCommunicateViewModel = new ViewModelProvider(requireActivity()).get(CommunicateViewModel.class);

        mCommunicateViewModel.needReloading.observe(getViewLifecycleOwner(), needLoading -> {
            if (needLoading) {
                onResume();
            }
        });

        setAdapterItems();
        setUpButtons();
        getAllData();

        return root;
    }

    /**
     * this method will listen a event of auto complete (month, continent)
     */
    public void setAdapterItems() {
        ArrayAdapter<String> adapterItemsMonth = new ArrayAdapter<>(getContext(), R.layout.dropdown_item, Constants.ITEMS_MONTH);
        ArrayAdapter<String> adapterItemsContinent = new ArrayAdapter<>(getContext(), R.layout.dropdown_item, Constants.ITEMS_CONTINENT);

        binding.autoCompleteMonth.setAdapter(adapterItemsMonth);
        binding.autoCompleteContinent.setAdapter(adapterItemsContinent);

        binding.autoCompleteMonth.setOnItemClickListener((adapterView, view, i, l) -> {
            month = adapterView.getItemAtPosition(i).toString();
            setDataForRecyclerView(month, continent);
        });

        binding.autoCompleteContinent.setOnItemClickListener((adapterView, view, i, l) -> {
            continent = adapterView.getItemAtPosition(i).toString();
            setDataForRecyclerView(month, continent);
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
            priceListAdapter.setImports(prepareDataForRecyclerView(month, continent));
            binding.priceListRcv.setLayoutManager(new LinearLayoutManager(getContext()));
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
    public List<ImportLcl> prepareDataForRecyclerView(String m, String c) {
        // reset a list when user choose different
        List<ImportLcl> list = new ArrayList<>();

        for (ImportLcl imp : listPriceList) {

            if (imp.getMonth().equalsIgnoreCase(m) && imp.getContinent().equalsIgnoreCase(c)) {
                list.add(imp);
            }
        }
        return list;
    }

    public List<ImportLcl> prepareDataForResume(String m, String c, List<ImportLcl> list) {
        // reset a list when user choose different
        List<ImportLcl> subList = new ArrayList<>();

        try {
            for (ImportLcl imp : list) {
                if (imp.getMonth().equalsIgnoreCase(m) && imp.getContinent().equalsIgnoreCase(c)) {
                    subList.add(imp);
                }
            }

        } catch (NullPointerException nullPointerException) {
            Toast.makeText(getContext(), nullPointerException.toString(), Toast.LENGTH_LONG).show();
        }
        return subList;
    }


    /**
     * this method will get all data from database
     */
    public void getAllData() {

        try {
            mImportViewModel.getImportList().observe(getViewLifecycleOwner(), detailsPojoImports ->
                    this.listPriceList = sortImportLcl(detailsPojoImports));
        } catch (NullPointerException exception) {
            Toast.makeText(getContext(), exception.toString(), Toast.LENGTH_LONG).show();
        }
    }

    public List<ImportLcl> sortImportLcl(List<ImportLcl> list){
        List<ImportLcl> result = new ArrayList<>();
        for(int i=list.size()-1; i>=0 ; i--){
            result.add(list.get(i));
        }
        return result;
    }

    @Override
    public void onResume() {
        super.onResume();
        priceListAdapter = new PriceListImportLclAdapter(getContext());
        mImportViewModel.getImportList().observe(getViewLifecycleOwner(), imp ->
                priceListAdapter.setImports(prepareDataForResume(month, continent, imp)));

        binding.priceListRcv.setAdapter(priceListAdapter);
    }

    /**
     * this method will set listen for buttons
     */
    public void setUpButtons() {
        binding.fragmentImportLclFab.setOnClickListener(this);

    }

    /**
     * this method used to set event for button click
     *
     * @param view click
     */
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fragment_import_lcl_fab) {
            DialogFragment dialogFragment = InsertImportLclDialog.getInstance();
            dialogFragment.show(getParentFragmentManager(), "Insert Dialog");
        }
    }
}