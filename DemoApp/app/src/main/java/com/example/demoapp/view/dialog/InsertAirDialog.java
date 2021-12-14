package com.example.demoapp.view.dialog;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.demoapp.R;
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

    private String selectItem;
//    private Button btnAdd, btnCancel;
//    private TextInputLayout et_pol, et_pod, et_dim, et_gross, et_type, et_airFreight,et_surcharge,
//          et_airlines, et_schedule, et_transit, et_valid, et_note;
    private FragmentDialogInsertAirBinding binding;

    private String linkURL = "http://192.168.1.90:80/database/";
    public static InsertAirDialog insertDiaLogAIR(){
        return new InsertAirDialog();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dialog_insert_air, container, false);

        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        et_pol = view.findViewById(R.id.tf_pol);
//        et_pod = view.findViewById(R.id.tf_pod);
//        et_dim = view.findViewById(R.id.tf_dim);
//        et_gross = view.findViewById(R.id.tf_gross);
//        et_type = view.findViewById(R.id.tf_typeofcargo);
//        et_airFreight = view.findViewById(R.id.tf_airfreight);
//        et_surcharge = view.findViewById(R.id.tf_surcharge);
//        et_airlines = view.findViewById(R.id.tf_airlines);
//        et_schedule = view.findViewById(R.id.tf_schedule);
//        et_transit = view.findViewById(R.id.tf_tf_transit_time);
//        et_valid = view.findViewById(R.id.tf_valid);
//        et_note = view.findViewById(R.id.tf_notes);
//
//        btnAdd = view.findViewById(R.id.btn_function_add);
//        btnCancel = view.findViewById(R.id.btn_function_cancel);

        binding.btnFunctionAdd.setOnClickListener(this);
        binding.btnFunctionCancel.setOnClickListener(this);

        setCancelable(false);
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
        String strAol = String.valueOf(Objects.requireNonNull(binding.etAolDialog).getText());
        String strAod = String.valueOf(Objects.requireNonNull(binding.etAodDialog).getText());
        String strDim = String.valueOf(Objects.requireNonNull(binding.etDimDialog).getText());
        String strGross = String.valueOf(Objects.requireNonNull(binding.etGrossDialog).getText());
        String strType = String.valueOf(Objects.requireNonNull(binding.etTypeDialog).getText());
        String strAIRFreight = String.valueOf(Objects.requireNonNull(binding.etFreightDialog).getText());
        String strSurcharge = String.valueOf(Objects.requireNonNull(binding.etSurchargeDialog).getText());
        String strAIRLines = String.valueOf(Objects.requireNonNull(binding.etLinesDialog).getText());
        String strSchedule = String.valueOf(Objects.requireNonNull(binding.etScheduleDialog).getText());
        String strTransit = String.valueOf(Objects.requireNonNull(binding.etTranmittimeDialog).getText());
        String strValid = String.valueOf(Objects.requireNonNull(binding.etValidDialog).getText());
        String strNote = String.valueOf(Objects.requireNonNull(binding.etNoteDialog).getText());
        String strMonth = "1";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(linkURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyAPI api = retrofit.create(MyAPI.class);
        Call<Air> call = api.addAIR(strAol, strAol, strDim, strGross, strType,
                strAIRFreight, strSurcharge, strAIRLines, strSchedule, strTransit, strValid, strNote
                ,strMonth);
        call.enqueue(new Callback<Air>() {
            @Override
            public void onResponse(Call<Air> call, Response<Air> response) {
                Toast.makeText(getContext(), response.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Air> call, Throwable t) {
                Toast.makeText(getContext(),"Insert AIR failure", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void resetEditText(){
        Objects.requireNonNull(binding.etAolDialog).setText("");
        Objects.requireNonNull(binding.etAodDialog).setText("");
        Objects.requireNonNull(binding.etDimDialog).setText("");
        Objects.requireNonNull(binding.etGrossDialog).setText("");
        Objects.requireNonNull(binding.etTypeDialog).setText("");
        Objects.requireNonNull(binding.etFreightDialog).setText("");
        Objects.requireNonNull(binding.etFreightDialog).setText("");
        Objects.requireNonNull(binding.etLinesDialog).setText("");
        Objects.requireNonNull(binding.etScheduleDialog).setText("");
        Objects.requireNonNull(binding.etTranmittimeDialog).setText("");
        Objects.requireNonNull(binding.etValidDialog).setText("");
        Objects.requireNonNull(binding.etNoteDialog).setText("");


    }
}