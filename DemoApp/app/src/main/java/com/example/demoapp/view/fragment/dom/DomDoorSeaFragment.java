package com.example.demoapp.view.fragment.dom;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.demoapp.R;
import com.example.demoapp.adapter.DoorSeaDomAdapter;
import com.example.demoapp.databinding.FragmentDomDoorSeaBinding;
import com.example.demoapp.model.DomDoorSea;
import com.example.demoapp.utilities.Constants;
import com.example.demoapp.view.dialog.dom.dom_door_sea.DialogDomDoorSeaInsert;
import com.example.demoapp.viewmodel.CommunicateViewModel;
import com.example.demoapp.viewmodel.DomDoorSeaViewModel;

import java.util.ArrayList;
import java.util.List;


public class DomDoorSeaFragment extends Fragment {

    private FragmentDomDoorSeaBinding binding;
    private DomDoorSeaViewModel mDomDoorSeaViewModel;
    private DoorSeaDomAdapter mDoorSeaDomAdapter;

    private List<DomDoorSea> mDomDoorSeaList = new ArrayList<>();

    private String month = "";
    private String continent = "";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDomDoorSeaBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        mDoorSeaDomAdapter = new DoorSeaDomAdapter(getContext());
        mDomDoorSeaViewModel = new ViewModelProvider(this).get(DomDoorSeaViewModel.class);

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
            mDoorSeaDomAdapter.setDomDoorSea(filterData(m, c));
            binding.rcvDomDoorSea.setAdapter(mDoorSeaDomAdapter);
            binding.rcvDomDoorSea.setLayoutManager(new LinearLayoutManager(getContext()));
        }
    }

    public List<DomDoorSea> filterData(String m, String c) {
        List<DomDoorSea> subList = new ArrayList<>();
        try {
            for (DomDoorSea domDoorSea : mDomDoorSeaList) {
                if (domDoorSea.getMonth().equalsIgnoreCase(m) && domDoorSea.getContinent().equalsIgnoreCase(c)) {
                    subList.add(domDoorSea);
                }
            }
        } catch (NullPointerException nullPointerException) {
            Toast.makeText(getContext(), nullPointerException.toString(), Toast.LENGTH_LONG).show();
        }
        return subList;
    }

    public List<DomDoorSea> filterDataResume(String m, String c, List<DomDoorSea> list) {
        List<DomDoorSea> subList = new ArrayList<>();
        try {
            for (DomDoorSea domDoorSea : list) {
                if (domDoorSea.getMonth().equalsIgnoreCase(m) && domDoorSea.getContinent().equalsIgnoreCase(c)) {
                    subList.add(domDoorSea);
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
        this.mDomDoorSeaList = new ArrayList<>();

        mDomDoorSeaViewModel.getAllData().observe(getViewLifecycleOwner(), domDoorSeas -> this.mDomDoorSeaList = domDoorSeas);
    }

    @Override
    public void onResume() {
        super.onResume();

        mDomDoorSeaViewModel.getAllData().observe(getViewLifecycleOwner(), domDoorSeas -> mDoorSeaDomAdapter.setDomDoorSea(filterDataResume(month, continent, domDoorSeas)));

        binding.rcvDomDoorSea.setAdapter(mDoorSeaDomAdapter);
    }

    public void setButtons() {
        binding.domDoorSeaFab.setOnClickListener(view -> {
            DialogFragment dialogFragment = DialogDomDoorSeaInsert.getInstance();
            dialogFragment.show(getChildFragmentManager(), "Door Sea Insert");
        });
    }
}