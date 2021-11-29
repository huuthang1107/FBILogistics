package com.example.demoapp.fragment_pricelist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.example.demoapp.R;
import com.example.demoapp.table_function.DetailsPojo;
import com.example.demoapp.table_function.FragmentDialogInsert;
import com.example.demoapp.table_function.GetAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PriceListAsia extends Fragment implements View.OnClickListener {

    private RadioButton radioGP, radioFR, radioRF, radioOT, radioHC;
    private TableRow tableRow1;
    private Button btnAdd, btnDel;

    private TableLayout tlPriceList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pricelist_asia, container, false);

        radioGP = view.findViewById(R.id.radio_gp);
        radioGP.setChecked(true);

        radioFR = view.findViewById(R.id.radio_fr);
        radioRF = view.findViewById(R.id.radio_rf);
        radioOT = view.findViewById(R.id.radio_ot);
        radioHC = view.findViewById(R.id.radio_hc);

        // table row 1
        tableRow1 = view.findViewById(R.id.table_price_row1);

        // table layout
        tlPriceList = view.findViewById(R.id.tl_price_list_table);

        // listener click add
        btnAdd = view.findViewById(R.id.btn_add_row);

        radioGP.setOnClickListener(this);
        // GP is clicked when loading
        radioGP.performClick();

        radioFR.setOnClickListener(this);
        radioRF.setOnClickListener(this);
        radioOT.setOnClickListener(this);
        radioHC.setOnClickListener(this);

        // add to table
        btnAdd.setOnClickListener(this);

        // call to show data into table
        process();

        return view;
    }

    // change column name when click radio button
    public void changeTableName(String str) {

        TextView view1 = (TextView) tableRow1.getChildAt(3);
        TextView view2 = (TextView) tableRow1.getChildAt(4);
        TextView view3 = (TextView) tableRow1.getChildAt(5);
        TextView view4 = (TextView) tableRow1.getChildAt(6);

        view1.setText(getString(R.string.col_of2).concat(str));
        view2.setText(getString(R.string.col_of4).concat(str));
        view3.setText(getString(R.string.col_su2).concat(str));
        view4.setText(getString(R.string.col_su4).concat(str));
    }

    // show data to tablelayout
    public void showDataTable() {

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        // which radio button is clicked
        switch (view.getId()) {
            case R.id.radio_gp:
                changeTableName("GP");
                break;
            case R.id.radio_fr:
                changeTableName("FR");
                break;
            case R.id.radio_rf:
                changeTableName("RF");
                break;
            case R.id.radio_ot:
                changeTableName("OT");
                break;
            case R.id.radio_hc:
                changeTableName("HC");
                break;
            case R.id.btn_add_row:
                DialogFragment dialogFragment = FragmentDialogInsert.insertDialog();
                dialogFragment.show(getParentFragmentManager(), "Insert Dialog");
        }
    }

    public List<TextView> getTextView(String stt, String pol, String pod, String of20,
                                      String of40, String su20, String su40,
                                      String line, String notes, String valid, String notes2) {

        TextView tv_stt = new TextView(getContext());
        tv_stt.setText(stt);
        tv_stt.setTextSize(18);
        tv_stt.setGravity(Gravity.CENTER);
        TextView tv_pol = new TextView(getContext());
        tv_pol.setText(pol);
        tv_pol.setTextSize(18);
        tv_pol.setGravity(Gravity.CENTER);
        TextView tv_pod = new TextView(getContext());
        tv_pod.setText(pod);
        tv_pod.setTextSize(18);
        tv_pod.setGravity(Gravity.CENTER);
        TextView tv_of20 = new TextView(getContext());
        tv_of20.setText(of20);
        tv_of20.setTextSize(18);
        tv_of20.setGravity(Gravity.CENTER);
        TextView tv_of40 = new TextView(getContext());
        tv_of40.setText(of40);
        tv_of40.setTextSize(18);
        tv_of40.setGravity(Gravity.CENTER);
        TextView tv_su20 = new TextView(getContext());
        tv_su20.setText(su20);
        tv_su20.setTextSize(18);
        tv_su20.setGravity(Gravity.CENTER);
        TextView tv_su40 = new TextView(getContext());
        tv_su40.setText(su40);
        tv_su40.setTextSize(18);
        tv_su40.setGravity(Gravity.CENTER);
        TextView tv_line = new TextView(getContext());
        tv_line.setText(line);
        tv_line.setTextSize(18);
        tv_line.setGravity(Gravity.CENTER);
        TextView tv_notes = new TextView(getContext());
        tv_notes.setText(notes);
        tv_notes.setTextSize(18);
        tv_notes.setGravity(Gravity.CENTER);
        TextView tv_valid = new TextView(getContext());
        tv_valid.setText(valid);
        tv_valid.setTextSize(18);
        tv_valid.setGravity(Gravity.CENTER);
        TextView tv_notes2 = new TextView(getContext());
        tv_notes2.setText(notes2);
        tv_notes2.setTextSize(18);
        tv_notes2.setGravity(Gravity.CENTER);

        List<TextView> textViewList = new ArrayList<>();
        textViewList.add(tv_stt);
        textViewList.add(tv_pol);
        textViewList.add(tv_pod);
        textViewList.add(tv_of20);
        textViewList.add(tv_of40);
        textViewList.add(tv_su20);
        textViewList.add(tv_su40);
        textViewList.add(tv_line);
        textViewList.add(tv_notes);
        textViewList.add(tv_valid);
        textViewList.add(tv_notes2);

        return textViewList;
    }

    // Get data from db to show into table
    public void process() {

        // Using retrofit library
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GetAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //initialize api class
        GetAPI getAPI = retrofit.create(GetAPI.class);

        // Fetching the values into Pojo File
        Call<List<DetailsPojo>> call = getAPI.getStatus();

        // call
        call.enqueue(new Callback<List<DetailsPojo>>() {
            @Override
            public void onResponse(@NonNull Call<List<DetailsPojo>> call, @NonNull Response<List<DetailsPojo>> response) {
                List<DetailsPojo> priceListData = response.body();

                assert priceListData != null;

                TableRow tableRow = new TableRow(getContext());
                for (int i = 0; i < priceListData.size() - 1; i++) {
                    for (int j = 0; j < 11; j++) {
                        tableRow.addView(getTextView(priceListData.get(i).getStt(),
                                priceListData.get(i).getPol(), priceListData.get(i).getPod(),
                                priceListData.get(i).getOf20(),priceListData.get(i).getOf40(),
                                priceListData.get(i).getSu20(),priceListData.get(i).getSu40(),
                                priceListData.get(i).getLinelist(),priceListData.get(i).getNotes(),
                                priceListData.get(i).getValid(),priceListData.get(i).getNotes2()).get(j));
                    }

                    tlPriceList.addView(tableRow);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<DetailsPojo>> call, @NonNull Throwable t) {

            }
        });
    }
}
