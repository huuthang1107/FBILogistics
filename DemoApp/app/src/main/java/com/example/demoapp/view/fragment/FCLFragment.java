package com.example.demoapp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.demoapp.R;
import com.example.demoapp.databinding.FragmentFclBinding;

public class FCLFragment extends Fragment {

    private FragmentFclBinding binding;

    private String[] itemsMonth = {"Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7",
            "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"};

    private String [] itemsContinent = {"Asia", "Europe", "America","Africa","Australia"};


    private ArrayAdapter<String> adapterItemsMonth, adapterItemsContinent;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
       binding = FragmentFclBinding.inflate(inflater, container, false);
       View view = binding.getRoot();

        setAdapterItems();

        return view;
    }

    public void setAdapterItems(){
        adapterItemsMonth = new ArrayAdapter<String>(getContext(), R.layout.dropdown_item, itemsMonth);
        adapterItemsContinent = new ArrayAdapter<String>(getContext(), R.layout.dropdown_item,itemsContinent);

        binding.autoCompleteMonth.setAdapter(adapterItemsMonth);
        binding.autoCompleteContinent.setAdapter(adapterItemsContinent);
        
    }
}
