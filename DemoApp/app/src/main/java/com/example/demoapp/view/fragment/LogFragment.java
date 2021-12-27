package com.example.demoapp.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.demoapp.R;
import com.example.demoapp.adapter.PriceListLogAdapter;
import com.example.demoapp.databinding.FragmentLogBinding;
import com.example.demoapp.model.Air;
import com.example.demoapp.model.Log;
import com.example.demoapp.view.dialog.InsertLogFragment;
import com.example.demoapp.viewmodel.LogViewModel;

import java.util.ArrayList;
import java.util.List;


public class LogFragment extends Fragment implements View.OnClickListener {

    private FragmentLogBinding logBinding;

    private final String[] itemsMonth = {"Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7",
            "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"};

    private final String[] itemsImportAndExport = {"Nhập Khẩu", "Xuất Khẩu"};

    private ArrayAdapter<String> adapterItemsMonth, adapterItemsImportAndExport;
    private String month = "";
    private String importAndExport = "";

    private PriceListLogAdapter mListLogAdapter;
    private LogViewModel mLogViewModel;
    private List<Log> mlistLog = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        logBinding =  FragmentLogBinding.inflate(inflater,container,false);
        View view = logBinding.getRoot();

        mListLogAdapter = new PriceListLogAdapter(getContext());
        mLogViewModel = new ViewModelProvider(this).get(LogViewModel.class);
        mLogViewModel.getLogList().observe(getViewLifecycleOwner(), log -> {
            mListLogAdapter.setDataLog(log);
        });

        getDataLog();
        setAdapterItems();
        setUpButtons();

        return view;
    }

    private void setUpButtons() {
        logBinding.fragmentLogFab.setOnClickListener(this);
    }

    private void setAdapterItems() {
        adapterItemsMonth = new ArrayAdapter<String>(getContext(), R.layout.dropdown_item, itemsMonth);
        adapterItemsImportAndExport= new ArrayAdapter<String>(getContext(), R.layout.dropdown_item, itemsImportAndExport);

        logBinding.autoCompleteMonthLog.setAdapter(adapterItemsMonth);
        logBinding.autoCompleteContinentLog.setAdapter(adapterItemsImportAndExport);

        logBinding.autoCompleteMonthLog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                month = adapterView.getItemAtPosition(i).toString();
                setDataForRecyclerView(month, importAndExport);
            }
        });

        logBinding.autoCompleteContinentLog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                importAndExport = adapterView.getItemAtPosition(i).toString();
                setDataForRecyclerView(month, importAndExport);
            }
        });
    }

    public void setDataForRecyclerView(String m, String c) {
        if (!m.isEmpty() && !c.isEmpty()) {
            logBinding.priceListRcv.setHasFixedSize(true);

            logBinding.priceListRcv.setLayoutManager(new LinearLayoutManager(getContext()));

            mListLogAdapter.setDataLog(prepareDataForRecyclerView(m,c));

            logBinding.priceListRcv.setAdapter(mListLogAdapter);
        }
    }

    private List<Log> prepareDataForRecyclerView(String m, String c) {
        List<Log> list = new ArrayList<>();

        for (Log a : mlistLog) {
            if (a.getMonth().equalsIgnoreCase(m) && a.getImportorexport().equalsIgnoreCase(c)) {
                list.add(a);
            }
        }
        return list;
    }


    private void getDataLog() {
        mlistLog = new ArrayList<>();
        mLogViewModel.getLogList().observe(getViewLifecycleOwner(), detailsPojoLog -> {
            this.mlistLog = detailsPojoLog;
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fragment_log_fab:
                DialogFragment dialogFragment = InsertLogFragment.insertDiaLogLog();
                dialogFragment.show(getParentFragmentManager(),"Insert dialog Log");
                break;
        }
    }
}