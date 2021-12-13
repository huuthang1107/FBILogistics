package com.example.demoapp.fragment_pricelistAIR;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableRow;

import com.example.demoapp.R;
import com.example.demoapp.adapter.PriceListViewAdapterLCL;
import com.example.demoapp.model.PriceListAIR;

import java.util.ArrayList;
import java.util.List;


public class PriceListAsiaFragment extends Fragment implements View.OnClickListener {

    private TableRow tableRow;
    private Button btnAdd, btnDel;

    private RecyclerView rcvRecyclerViewAsia;
    PriceListViewAdapterLCL priceListViewAdapterLCL;

    List<PriceListAIR> listPriceList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_price_list_asia, container, false);

        tableRow = view.findViewById(R.id.table_price_row1);
        rcvRecyclerViewAsia = view.findViewById(R.id.price_list_asia_rcv);

        btnAdd = view.findViewById(R.id.btn_add_row);

        btnAdd.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        DialogFragment dialogFragment = DialogInsertFragment.insertDiaLogAIR();
        dialogFragment.show(getParentFragmentManager(),"Insert DiaLog AIR");
    }
}