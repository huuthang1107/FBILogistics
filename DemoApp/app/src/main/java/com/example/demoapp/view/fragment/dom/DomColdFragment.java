package com.example.demoapp.view.fragment.dom;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.demoapp.R;
import com.example.demoapp.adapter.ColdDomAdapter;
import com.example.demoapp.databinding.FragmentDomColdBinding;
import com.example.demoapp.model.DomCold;
import com.example.demoapp.utilities.Constants;
import com.example.demoapp.view.dialog.dom.dom_cold.DialogDomColdInsert;
import com.example.demoapp.viewmodel.CommunicateViewModel;
import com.example.demoapp.viewmodel.DomColdViewModel;

import java.util.ArrayList;
import java.util.List;


public class DomColdFragment extends Fragment {

    private FragmentDomColdBinding binding;
    private DomColdViewModel mDomColdViewModel;
    private ColdDomAdapter mColdDomAdapter;

    private List<DomCold> mDomColdList = new ArrayList<>();

    private String month = "";
    private String continent = "";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDomColdBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        mColdDomAdapter = new ColdDomAdapter(getContext());
        mDomColdViewModel = new ViewModelProvider(this).get(DomColdViewModel.class);

        CommunicateViewModel mCommunicateViewModel = new ViewModelProvider(requireActivity()).get(CommunicateViewModel.class);

        mCommunicateViewModel.needReloading.observe(getViewLifecycleOwner(), needLoading -> {
            if (needLoading) {
                onResume();
            }
        });

        getAllData();
        setAutoComplete();
        setButtons();

        return view;
    }

    public void setUpRecyclerView(String m, String c) {
        if (!m.isEmpty() && !c.isEmpty()) {
            mColdDomAdapter.setDomCold(filterData(m, c));
            binding.rcvDomCold.setAdapter(mColdDomAdapter);
            binding.rcvDomCold.setLayoutManager(new LinearLayoutManager(getContext()));
        }
    }

    public List<DomCold> filterData(String m, String c) {
        List<DomCold> subList = new ArrayList<>();
        try {
            for (DomCold domCold : mDomColdList) {
                if (domCold.getMonth().equalsIgnoreCase(m) && domCold.getContinent().equalsIgnoreCase(c)) {
                    subList.add(domCold);
                }
            }
        } catch (NullPointerException nullPointerException) {
            Toast.makeText(getContext(), nullPointerException.toString(), Toast.LENGTH_LONG).show();
        }
        return subList;
    }

    public List<DomCold> filterDataResume(String m, String c, List<DomCold> list) {
        List<DomCold> subList = new ArrayList<>();
        try {
            for (DomCold domCold : list) {
                if (domCold.getMonth().equalsIgnoreCase(m) && domCold.getContinent().equalsIgnoreCase(c)) {
                    subList.add(domCold);
                }
            }
        } catch (NullPointerException nullPointerException) {
            Toast.makeText(getContext(), nullPointerException.toString(), Toast.LENGTH_LONG).show();
        }
        return subList;
    }

    public void setAutoComplete() {
        ArrayAdapter<String> adapterItemsMonth = new ArrayAdapter<>(getContext(), R.layout.dropdown_item, Constants.ITEMS_MONTH);
        ArrayAdapter<String> adapterItemsContinent = new ArrayAdapter<>(getContext(), R.layout.dropdown_item, Constants.ITEMS_CONTINENT);

        binding.autoDomMonth.setAdapter(adapterItemsMonth);
        binding.autoDomContinent.setAdapter(adapterItemsContinent);

        binding.autoDomMonth.setOnItemClickListener((adapterView, view, i, l) -> {
            month = adapterView.getItemAtPosition(i).toString();
            setUpRecyclerView(month, continent);
        });

        binding.autoDomContinent.setOnItemClickListener((adapterView, view, i, l) -> {
            continent = adapterView.getItemAtPosition(i).toString();
            setUpRecyclerView(month, continent);
        });
    }

    public void getAllData() {
        this.mDomColdList = new ArrayList<>();

        mDomColdViewModel.getAllData().observe(getViewLifecycleOwner(), domColds -> this.mDomColdList = domColds);
    }

    @Override
    public void onResume() {
        super.onResume();

        mDomColdViewModel.getAllData().observe(getViewLifecycleOwner(), domColds -> mColdDomAdapter.setDomCold(filterDataResume(month, continent, domColds)));

        binding.rcvDomCold.setAdapter(mColdDomAdapter);
    }

    public void setButtons() {
        binding.domColdFab.setOnClickListener(view -> {
            DialogFragment dialogFragment = DialogDomColdInsert.getInstance();
            dialogFragment.show(getChildFragmentManager(), "Dry Insert");
        });
    }
}