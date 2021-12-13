package com.example.demoapp.fragment_pricelistAIR;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.demoapp.R;
import com.example.demoapp.db.MyAPI;
import com.example.demoapp.model.PriceListAIR;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class DialogInsertFragment extends DialogFragment implements  View.OnClickListener {

    private String selectItem;
    private Button btnAdd, btnCancel;
    private TextInputLayout et_pol, et_pod, et_dim, et_gross, et_type, et_airFreight,et_surcharge,
          et_airlines, et_schedule, et_transit, et_valid, et_note;

    private String linkURL = "http://192.168.1.90:80/database/";
    public static DialogInsertFragment insertDiaLogAIR(){
        return new DialogInsertFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dialog_insert_air, container, false);

        et_pol = view.findViewById(R.id.tf_pol);
        et_pod = view.findViewById(R.id.tf_pod);
        et_dim = view.findViewById(R.id.tf_dim);
        et_gross = view.findViewById(R.id.tf_gross);
        et_type = view.findViewById(R.id.tf_typeofcargo);
        et_airFreight = view.findViewById(R.id.tf_airfreight);
        et_surcharge = view.findViewById(R.id.tf_surcharge);
        et_airlines = view.findViewById(R.id.tf_airlines);
        et_schedule = view.findViewById(R.id.tf_schedule);
        et_transit = view.findViewById(R.id.tf_tf_transit_time);
        et_valid = view.findViewById(R.id.tf_valid);
        et_note = view.findViewById(R.id.tf_notes);

        btnAdd = view.findViewById(R.id.btn_function_add);
        btnCancel = view.findViewById(R.id.btn_function_cancel);
        
        btnAdd.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        
        setCancelable(false);

        return  view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_function_add:
//                insertAIR();
                Toast.makeText(getContext(),"Insert AIR failure", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_function_cancel:
                dismiss();
                break;
        }
    }

    private void insertAIR() {
        String strAol = String.valueOf(Objects.requireNonNull(et_pol.getEditText()).getText());
        String strAod = String.valueOf(Objects.requireNonNull(et_pod.getEditText()).getText());
        String strDim = String.valueOf(Objects.requireNonNull(et_dim.getEditText()).getText());
        String strGross = String.valueOf(Objects.requireNonNull(et_gross.getEditText()).getText());
        String strType = String.valueOf(Objects.requireNonNull(et_type.getEditText()).getText());
        String strAIRFreight = String.valueOf(Objects.requireNonNull(et_airFreight.getEditText()).getText());
        String strSurcharge = String.valueOf(Objects.requireNonNull(et_surcharge.getEditText()).getText());
        String strAIRLines = String.valueOf(Objects.requireNonNull(et_airlines.getEditText()).getText());
        String strSchedule = String.valueOf(Objects.requireNonNull(et_schedule.getEditText()).getText());
        String strTransit = String.valueOf(Objects.requireNonNull(et_transit.getEditText()).getText());
        String strValid = String.valueOf(Objects.requireNonNull(et_valid.getEditText()).getText());
        String strNote = String.valueOf(Objects.requireNonNull(et_note.getEditText()).getText());
        String strMonth = "1";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(linkURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyAPI api = retrofit.create(MyAPI.class);
        Call<PriceListAIR> call = api.addAIR(strAol, strAol, strDim, strGross, strType,
                strAIRFreight, strSurcharge, strAIRLines, strSchedule, strTransit, strValid, strNote
                ,strMonth);
        call.enqueue(new Callback<PriceListAIR>() {
            @Override
            public void onResponse(Call<PriceListAIR> call, Response<PriceListAIR> response) {
                Toast.makeText(getContext(), response.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PriceListAIR> call, Throwable t) {
                Toast.makeText(getContext(),"Insert AIR failure", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void resetEditText(){
        Objects.requireNonNull(et_pod.getEditText()).setText("");
        Objects.requireNonNull(et_pod.getEditText()).setText("");
        Objects.requireNonNull(et_dim.getEditText()).setText("");
        Objects.requireNonNull(et_gross.getEditText()).setText("");
        Objects.requireNonNull(et_type.getEditText()).setText("");
        Objects.requireNonNull(et_airFreight.getEditText()).setText("");
        Objects.requireNonNull(et_surcharge.getEditText()).setText("");
        Objects.requireNonNull(et_airlines.getEditText()).setText("");
        Objects.requireNonNull(et_schedule.getEditText()).setText("");
        Objects.requireNonNull(et_transit.getEditText()).setText("");
        Objects.requireNonNull(et_valid.getEditText()).setText("");
        Objects.requireNonNull(et_note.getEditText()).setText("");


    }
}