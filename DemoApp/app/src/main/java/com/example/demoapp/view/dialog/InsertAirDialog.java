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
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;


public class InsertAirDialog extends DialogFragment implements  View.OnClickListener {

    private String selectItem;
    private Button btnAdd, btnCancel;
    private TextInputLayout et_pol, et_pod, et_dim, et_gross, et_type, et_airFreight,et_surcharge,
          et_airlines, et_schedule, et_transit, et_valid, et_note;

    public static InsertAirDialog insertDiaLogAIR(){
        return new InsertAirDialog();
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