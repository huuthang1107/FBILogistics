package com.example.demoapp.view.fragment.sales;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.demoapp.R;
import com.example.demoapp.adapter.sale.PriceListDoorDomAdapter;
import com.example.demoapp.databinding.FragmentDoorToDoorBinding;
import com.example.demoapp.model.DomDoor;
import com.example.demoapp.utilities.Constants;
import com.example.demoapp.viewmodel.CommunicateViewModel;
import com.example.demoapp.viewmodel.DomDoorViewModel;

import java.util.ArrayList;
import java.util.List;

public class DoorToDoorFragment extends Fragment {

    private FragmentDoorToDoorBinding binding;
    private DomDoorViewModel mDomDoorViewModel;
    private PriceListDoorDomAdapter mDoorDomAdapter;

    private List<DomDoor> mDomDoorList = new ArrayList<>();

    private String month = "";
    private String continent = "";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentDoorToDoorBinding.inflate(inflater, container, false);

        View view = binding.getRoot();

        mDoorDomAdapter = new PriceListDoorDomAdapter(getContext());
        mDomDoorViewModel = new ViewModelProvider(this).get(DomDoorViewModel.class);

        CommunicateViewModel mCommunicateViewModel = new ViewModelProvider(requireActivity()).get(CommunicateViewModel.class);

        mCommunicateViewModel.needReloading.observe(getViewLifecycleOwner(), needLoading -> {
            if (needLoading) {
                onResume();
            }
        });

        getAllData();
        setAutoComplete();

        return view;
    }

    public void setUpRecyclerView(String m, String c) {
        if (!m.isEmpty() && !c.isEmpty()) {
            mDoorDomAdapter.setDomDoor(filterData(m, c));
            binding.rcvDomDoor.setAdapter(mDoorDomAdapter);
            binding.rcvDomDoor.setLayoutManager(new LinearLayoutManager(getContext()));
        }
    }

    public List<DomDoor> filterData(String m, String c) {
        List<DomDoor> subList = new ArrayList<>();
        try {
            for (DomDoor domDoor : mDomDoorList) {
                if (domDoor.getMonth().equalsIgnoreCase(m) && domDoor.getContinent().equalsIgnoreCase(c)) {
                    subList.add(domDoor);
                }
            }
        } catch (NullPointerException nullPointerException) {
            Toast.makeText(getContext(), nullPointerException.toString(), Toast.LENGTH_LONG).show();
        }
        return subList;
    }

    public List<DomDoor> filterDataResume(String m, String c, List<DomDoor> list) {
        List<DomDoor> subList = new ArrayList<>();
        try {
            for (DomDoor domDoor : list) {
                if (domDoor.getMonth().equalsIgnoreCase(m) && domDoor.getContinent().equalsIgnoreCase(c)) {
                    subList.add(domDoor);
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
        this.mDomDoorList = new ArrayList<>();

        mDomDoorViewModel.getAllData().observe(getViewLifecycleOwner(), domDoors -> this.mDomDoorList = domDoors);
    }

    @Override
    public void onResume() {
        super.onResume();

        mDomDoorViewModel.getAllData().observe(getViewLifecycleOwner(), domDoors -> mDoorDomAdapter.setDomDoor(filterDataResume(month, continent, domDoors)));

        binding.rcvDomDoor.setAdapter(mDoorDomAdapter);
    }


}