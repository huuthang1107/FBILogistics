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
import com.example.demoapp.adapter.CyDomAdapter;
import com.example.demoapp.databinding.FragmentDomCyBinding;
import com.example.demoapp.model.DomCy;
import com.example.demoapp.utilities.Constants;
import com.example.demoapp.view.dialog.dom.dom_cy.DialogDomCyInsert;
import com.example.demoapp.viewmodel.CommunicateViewModel;
import com.example.demoapp.viewmodel.DomCyViewModel;

import java.util.ArrayList;
import java.util.List;

public class DomCyFragment extends Fragment {

    private FragmentDomCyBinding binding;
    private DomCyViewModel mDomCyViewModel;
    private CyDomAdapter mCyDomAdapter;

    private List<DomCy> mDomCyList = new ArrayList<>();

    private String month = "";
    private String continent = "";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDomCyBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        mCyDomAdapter = new CyDomAdapter(getContext());
        mDomCyViewModel = new ViewModelProvider(this).get(DomCyViewModel.class);

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
            mCyDomAdapter.setDomCy(filterData(m, c));
            binding.rcvDomCy.setAdapter(mCyDomAdapter);
            binding.rcvDomCy.setLayoutManager(new LinearLayoutManager(getContext()));
        }
    }

    public List<DomCy> filterData(String m, String c) {
        List<DomCy> subList = new ArrayList<>();
        try {
            for (DomCy domCy : mDomCyList) {
                if (domCy.getMonth().equalsIgnoreCase(m) && domCy.getContinent().equalsIgnoreCase(c)) {
                    subList.add(domCy);
                }
            }
        } catch (NullPointerException nullPointerException) {
            Toast.makeText(getContext(), nullPointerException.toString(), Toast.LENGTH_LONG).show();
        }
        return subList;
    }

    public List<DomCy> filterDataResume(String m, String c, List<DomCy> list) {
        List<DomCy> subList = new ArrayList<>();
        try {
            for (DomCy domCy : list) {
                if (domCy.getMonth().equalsIgnoreCase(m) && domCy.getContinent().equalsIgnoreCase(c)) {
                    subList.add(domCy);
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
        this.mDomCyList = new ArrayList<>();

        mDomCyViewModel.getAllData().observe(getViewLifecycleOwner(), domCy -> this.mDomCyList = domCy);
    }

    @Override
    public void onResume() {
        super.onResume();

        mDomCyViewModel.getAllData().observe(getViewLifecycleOwner(), domCy -> mCyDomAdapter.setDomCy(filterDataResume(month, continent, domCy)));

        binding.rcvDomCy.setAdapter(mCyDomAdapter);
    }

    public void setButtons() {
        binding.domCyFab.setOnClickListener(view -> {
            DialogFragment dialogFragment = DialogDomCyInsert.getInstance();
            dialogFragment.show(getChildFragmentManager(), "Cy Insert");
        });
    }
}