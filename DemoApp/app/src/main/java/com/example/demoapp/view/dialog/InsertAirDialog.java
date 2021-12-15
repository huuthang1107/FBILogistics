package com.example.demoapp.view.dialog;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.demoapp.R;
import com.example.demoapp.api.InsertAIR;
import com.example.demoapp.api.MyAPI;
import com.example.demoapp.databinding.FragmentDialogInsertAirBinding;
import com.example.demoapp.model.Air;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class InsertAirDialog extends DialogFragment implements  View.OnClickListener {

    FragmentDialogInsertAirBinding insertAirBinding;

    private final String[] itemsMonth = {"Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7",
            "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"};

    private final String[] itemsContinent = {"Asia", "Europe", "America", "Africa", "Australia"};

    private final String[] listStr = new String[2];

    private ArrayAdapter<String>  adapterItemsMonth, adapterItemsContinent;

    private String linkURL = "http://192.168.1.180/dataAIR/";
    public static InsertAirDialog insertDiaLogAIR(){
        return new InsertAirDialog();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        insertAirBinding = FragmentDialogInsertAirBinding.inflate(inflater, container, false);

        View view = insertAirBinding.getRoot();

        initView();

        return view;

    }

    private void initView() {
        adapterItemsMonth = new ArrayAdapter<String>(getContext(), R.layout.dropdown_item, itemsMonth);
        adapterItemsContinent = new ArrayAdapter<String>(getContext(), R.layout.dropdown_item, itemsContinent);

        insertAirBinding.insertAutoMonth.setAdapter(adapterItemsMonth);
        insertAirBinding.insertAutoContinent.setAdapter(adapterItemsContinent);

        insertAirBinding.btnFunctionAdd.setOnClickListener(this);
        insertAirBinding.btnFunctionCancel.setOnClickListener(this);

        insertAirBinding.insertAutoMonth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listStr[0] = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getContext(), listStr[0], Toast.LENGTH_LONG).show();
            }
        });

        insertAirBinding.insertAutoContinent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listStr[1] = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getContext(), listStr[1], Toast.LENGTH_LONG).show();
            }
        });

        //dialog
        setCancelable(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_function_add:
                insertAIR();
                break;
            case R.id.btn_function_cancel:
                dismiss();
                break;
        }
    }

    private void insertAIR() {
        String strAol = insertAirBinding.tfPol.getEditText().getText().toString();
        String strAod = insertAirBinding.tfPod.getEditText().getText().toString();
        String strDim = insertAirBinding.tfDim.getEditText().getText().toString();
        String strGross = insertAirBinding.tfGross.getEditText().getText().toString();
        String strType = insertAirBinding.tfTypeofcargo.getEditText().getText().toString();
        String strAIRFreight = insertAirBinding.tfAirfreight.getEditText().getText().toString();
        String strSurcharge = insertAirBinding.tfSurcharge.getEditText().getText().toString();
        String strAIRLines = insertAirBinding.tfAirlines.getEditText().getText().toString();
        String strSchedule = insertAirBinding.tfSchedule.getEditText().getText().toString();
        String strTransit = insertAirBinding.tfTfTransitTime.getEditText().getText().toString();
        String strValid = insertAirBinding.tfValid.getEditText().getText().toString();
        String strNote = insertAirBinding.tfNotes.getEditText().getText().toString();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(linkURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InsertAIR api = retrofit.create(InsertAIR.class);

        Call<Air> call = api.addAIR(strAol, strAol, strDim, strGross, strType,
                strAIRFreight, strSurcharge, strAIRLines, strSchedule, strTransit, strValid, strNote
                ,listStr[0], listStr[1]);

        call.enqueue(new Callback<Air>() {
            @Override
            public void onResponse(Call<Air> call, Response<Air> response) {
                Toast.makeText(getContext(), response.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Air> call, Throwable t) {
                Toast.makeText(getContext(),"Insert AIR Successful", Toast.LENGTH_SHORT).show();
            }
        });
        resetEditText();

    }

    public void resetEditText(){
        Objects.requireNonNull(insertAirBinding.tfPod.getEditText()).setText("");
        Objects.requireNonNull(insertAirBinding.tfPol.getEditText()).setText("");
        Objects.requireNonNull(insertAirBinding.tfDim.getEditText()).setText("");
        Objects.requireNonNull(insertAirBinding.tfGross.getEditText()).setText("");
        Objects.requireNonNull(insertAirBinding.tfTypeofcargo.getEditText()).setText("");
        Objects.requireNonNull(insertAirBinding.tfAirfreight.getEditText()).setText("");
        Objects.requireNonNull(insertAirBinding.tfSurcharge.getEditText()).setText("");
        Objects.requireNonNull(insertAirBinding.tfAirlines.getEditText()).setText("");
        Objects.requireNonNull(insertAirBinding.tfSchedule.getEditText()).setText("");
        Objects.requireNonNull(insertAirBinding.tfTfTransitTime.getEditText()).setText("");
        Objects.requireNonNull(insertAirBinding.tfValid.getEditText()).setText("");
        Objects.requireNonNull(insertAirBinding.tfNotes.getEditText()).setText("");

    }
}