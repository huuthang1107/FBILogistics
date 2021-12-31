package com.example.demoapp.view.fragment.dom;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.demoapp.R;
import com.example.demoapp.adapter.ExportDomAdapter;
import com.example.demoapp.adapter.PriceListFclAdapter;
import com.example.demoapp.databinding.FragmentDomExportBinding;
import com.example.demoapp.model.DomExport;
import com.example.demoapp.model.Fcl;
import com.example.demoapp.utilities.Constants;
import com.example.demoapp.view.dialog.dom.DialogInsertDomExport;
import com.example.demoapp.viewmodel.CommunicateViewModel;
import com.example.demoapp.viewmodel.DomExportViewModel;

import java.util.ArrayList;
import java.util.List;


public class DomExportFragment extends Fragment implements View.OnClickListener {

    private FragmentDomExportBinding binding;
    private DomExportViewModel mDomExportViewModel;
    private ExportDomAdapter mExportDomAdapter;

    private List<DomExport> mDomExportList = new ArrayList<>();

    private String month = "";
    private String continent = "";
    private String radioItem = "All";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DomExportFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DomExportFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DomExportFragment newInstance(String param1, String param2) {
        DomExportFragment fragment = new DomExportFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDomExportBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        mDomExportViewModel = new ViewModelProvider(this).get(DomExportViewModel.class);

        CommunicateViewModel communicateViewModel = new ViewModelProvider(this).get(CommunicateViewModel.class);
        communicateViewModel.needReloading.observe(getViewLifecycleOwner(), reloading -> {
            if ((reloading)) {
                Log.d("testload", String.valueOf(reloading.toString()));
                onResume();
            }
        });

        getAllData();
        setAutoComplete();
        setRadios();
        setButton();

        return view;
    }

    public void setRadios() {

        binding.radioExportAll.setOnClickListener(this);
        binding.radioExportAll.performClick();

        binding.radioExportFr.setOnClickListener(this);

        binding.radioExportRf.setOnClickListener(this);

        binding.radioExportOt.setOnClickListener(this);

        binding.radioExportIso.setOnClickListener(this);

        binding.radioExportFt.setOnClickListener(this);
    }

    public void setUpRecyclerView(String m, String c, String r) {
        if (!m.isEmpty() && !c.isEmpty()) {
            mExportDomAdapter.setDomExport(filterData(m, c, r));
            binding.rcvDomExport.setAdapter(mExportDomAdapter);
            binding.rcvDomExport.setLayoutManager(new LinearLayoutManager(getContext()));
        }
    }

    public List<DomExport> filterData(String m, String c, String r) {
        List<DomExport> subList = new ArrayList<>();
        try {
            for (DomExport domExport : mDomExportList) {
                if (r.equalsIgnoreCase("all")) {
                    if (domExport.getMonth().equalsIgnoreCase(m) && domExport.getContinent().equalsIgnoreCase(c)) {
                        subList.add(domExport);
                    }
                } else {
                    if (domExport.getMonth().equalsIgnoreCase(m) && domExport.getContinent().equalsIgnoreCase(c)
                            && domExport.getType().equalsIgnoreCase(r)) {
                        subList.add(domExport);
                    }
                }
            }
        } catch (NullPointerException nullPointerException) {
            Toast.makeText(getContext(), nullPointerException.toString(), Toast.LENGTH_LONG).show();
        }
        return subList;
    }

    public List<DomExport> filterDataResume(String m, String c, String r, List<DomExport> list) {
        List<DomExport> subList = new ArrayList<>();
        try {
            for (DomExport domExport : list) {
                if (r.equalsIgnoreCase("all")) {
                    if (domExport.getMonth().equalsIgnoreCase(m) && domExport.getContinent().equalsIgnoreCase(c)) {
                        subList.add(domExport);
                    }
                } else {
                    if (domExport.getMonth().equalsIgnoreCase(m) && domExport.getContinent().equalsIgnoreCase(c)
                            && domExport.getType().equalsIgnoreCase(r)) {
                        subList.add(domExport);
                    }
                }
            }
        } catch (NullPointerException nullPointerException) {
            Toast.makeText(getContext(), nullPointerException.toString(), Toast.LENGTH_LONG).show();
        }
        return subList;
    }

    public void setAutoComplete() {
        ArrayAdapter<String> adapterItemsMonth = new ArrayAdapter<String>(getContext(), R.layout.dropdown_item, Constants.ITEMS_MONTH);
        ArrayAdapter<String> adapterItemsContinent = new ArrayAdapter<String>(getContext(), R.layout.dropdown_item, Constants.ITEMS_CONTINENT);

        binding.autoDomMonth.setAdapter(adapterItemsMonth);
        binding.autoDomContinent.setAdapter(adapterItemsContinent);

        binding.autoDomMonth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                month = adapterView.getItemAtPosition(i).toString();
                setUpRecyclerView(month, continent, radioItem);
            }
        });

        binding.autoDomContinent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                continent = adapterView.getItemAtPosition(i).toString();
                setUpRecyclerView(month, continent, radioItem);
            }
        });
    }

    public void getAllData() {
        this.mDomExportList = new ArrayList<>();

        mDomExportViewModel.getAllData().observe(getViewLifecycleOwner(), domExports -> {
            this.mDomExportList = domExports;
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        mExportDomAdapter = new ExportDomAdapter(getContext());
        mDomExportViewModel.getAllData().observe(getViewLifecycleOwner(), domExports -> {
            mExportDomAdapter.setDomExport(filterDataResume(month, continent, radioItem, domExports));
        });

        binding.rcvDomExport.setAdapter(mExportDomAdapter);
    }

    public void setButton() {
        binding.domExportFab.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.dom_export_fab:
                DialogFragment dialogFragment = DialogInsertDomExport.getInstance();
                dialogFragment.show(getChildFragmentManager(), "ExportDom");
        }
    }

}