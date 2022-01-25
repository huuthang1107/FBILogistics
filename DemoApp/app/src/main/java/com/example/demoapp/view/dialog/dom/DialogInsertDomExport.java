package com.example.demoapp.view.dialog.dom;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.demoapp.R;
import com.example.demoapp.databinding.DialogInsertDomExportBinding;
import com.example.demoapp.model.DomExport;
import com.example.demoapp.utilities.Constants;
import com.example.demoapp.viewmodel.CommunicateViewModel;
import com.example.demoapp.viewmodel.DomExportViewModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DialogInsertDomExport extends DialogFragment implements View.OnClickListener {

    private DialogInsertDomExportBinding binding;

    private final String[] listStr = new String[3];

    private String name, weight, quantity, temp, address, portExport, length, height, width;

    private DomExportViewModel mDomExportViewModel;
    private CommunicateViewModel communicateViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DialogInsertDomExportBinding.inflate(inflater, container, false);

        View view = binding.getRoot();

        communicateViewModel = new ViewModelProvider(requireActivity()).get(CommunicateViewModel.class);

        setUpViews();

        return view;
    }

    public static DialogInsertDomExport getInstance() {

        return new DialogInsertDomExport();
    }

    private void setUpViews() {

        binding.btnDomExportInsert.setOnClickListener(this);
        binding.btnDomExportCancel.setOnClickListener(this);
        mDomExportViewModel = new ViewModelProvider(this).get(DomExportViewModel.class);

        ArrayAdapter<String> adapterItemsType = new ArrayAdapter<>(getContext(), R.layout.dropdown_item, Constants.ITEMS_DOM);
        ArrayAdapter<String> adapterItemsMonth = new ArrayAdapter<>(getContext(), R.layout.dropdown_item, Constants.ITEMS_MONTH);
        ArrayAdapter<String> adapterItemsContinent = new ArrayAdapter<>(getContext(), R.layout.dropdown_item, Constants.ITEMS_CONTINENT);

        binding.domExportInsertAutoContainer.setAdapter(adapterItemsType);
        binding.domExportInsertAutoMonth.setAdapter(adapterItemsMonth);
        binding.domExportInsertAutoContinent.setAdapter(adapterItemsContinent);

        binding.domExportInsertAutoContainer.setOnItemClickListener((adapterView, view, i, l) ->
                listStr[0] = adapterView.getItemAtPosition(i).toString());

        binding.domExportInsertAutoMonth.setOnItemClickListener((adapterView, view, i, l) ->
                listStr[1] = adapterView.getItemAtPosition(i).toString());

        binding.domExportInsertAutoContinent.setOnItemClickListener((adapterView, view, i, l) ->
                listStr[2] = adapterView.getItemAtPosition(i).toString());

        setCancelable(false);
    }

    /**
     * Get current date and time
     * @return current date and time
     */
    private String getCreatedDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {
            case R.id.btn_dom_export_insert:
                insertData();
                dismiss();
                break;
            case R.id.btn_dom_export_cancel:
                dismiss();
                break;
        }
    }

    public void getDataFromForm() {
        name = Objects.requireNonNull(binding.insertDomExportName.getEditText()).getText().toString();
        weight = Objects.requireNonNull(binding.insertDomExportWeight.getEditText()).getText().toString();
        quantity = Objects.requireNonNull(binding.insertDomExportQuantity.getEditText()).getText().toString();
        temp = Objects.requireNonNull(binding.insertDomExportTemp.getEditText()).getText().toString();
        address = Objects.requireNonNull(binding.insertDomExportAddress.getEditText()).getText().toString();
        portExport = Objects.requireNonNull(binding.insertDomExportPort.getEditText()).getText().toString();
        length = Objects.requireNonNull(binding.insertDomExportLength.getEditText()).getText().toString();
        height = Objects.requireNonNull(binding.insertDomExportHeight.getEditText()).getText().toString();
        width = Objects.requireNonNull(binding.insertDomExportWidth.getEditText()).getText().toString();
    }

    public void insertData() {
        getDataFromForm();

        communicateViewModel.makeChanges();

        mDomExportViewModel.insertData(name, weight, quantity, temp, address, portExport, length,
                height, width, listStr[0], listStr[1], listStr[2], getCreatedDate()).enqueue(new Callback<DomExport>() {
            @Override
            public void onResponse(@NonNull Call<DomExport> call, @NonNull Response<DomExport> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "Insert Successful!!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<DomExport> call, @NonNull Throwable t) {

            }
        });
    }
}
