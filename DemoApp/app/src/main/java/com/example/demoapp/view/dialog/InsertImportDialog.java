package com.example.demoapp.view.dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.demoapp.R;
import com.example.demoapp.model.Import;
import com.example.demoapp.services.ImportService;
import com.example.demoapp.databinding.FragmentDialogInsertImportBinding;
import com.example.demoapp.utilities.APIClient;
import com.example.demoapp.utilities.Constants;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertImportDialog extends DialogFragment implements View.OnClickListener {

    private final String[] itemsType = {"GP", "FR", "RF", "OT", "HQ", "TK"};

    private final String[] itemsMonth = {"Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7",
            "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"};

    private final String[] itemsContinent = {"Asia", "Europe", "America", "Africa", "Australia"};

    private final String[] listStr = new String[3];

    FragmentDialogInsertImportBinding binding;

    /**
     * This method will set up view for insert dialog
     * @param inflater inflater
     * @param container container
     * @param savedInstanceState save
     * @return view of Import insert dialog
     */

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentDialogInsertImportBinding.inflate(inflater, container, false);

        View view = binding.getRoot();

        initView();

        return view;
    }

    /**
     * This method will init for views and get item from auto complete text view
     */
    public void initView() {

        // auto complete textview
        ArrayAdapter<String> adapterItemsType = new ArrayAdapter<String>(getContext(), R.layout.dropdown_item, itemsType);
        ArrayAdapter<String> adapterItemsMonth = new ArrayAdapter<String>(getContext(), R.layout.dropdown_item, itemsMonth);
        ArrayAdapter<String> adapterItemsContinent = new ArrayAdapter<String>(getContext(), R.layout.dropdown_item, itemsContinent);

        binding.insertAutoContainer.setAdapter(adapterItemsType);
        binding.insertAutoMonth.setAdapter(adapterItemsMonth);
        binding.insertAutoContinent.setAdapter(adapterItemsContinent);

        // buttons
        binding.btnFunctionAdd.setOnClickListener(this);
        binding.btnFunctionCancel.setOnClickListener(this);

        binding.insertAutoContainer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listStr[0] = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getContext(), listStr[0], Toast.LENGTH_LONG).show();
            }
        });

        binding.insertAutoMonth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listStr[1] = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getContext(), listStr[1], Toast.LENGTH_LONG).show();
            }
        });

        binding.insertAutoContinent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listStr[2] = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getContext(), listStr[2], Toast.LENGTH_LONG).show();
            }
        });

        setCancelable(false);

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_function_add:
                process();
                break;
            case R.id.btn_function_cancel:
                dismiss();
                break;
        }
    }

    public static InsertImportDialog insertDialog() {
        return new InsertImportDialog();
    }

    /**
     * This method will get information user typing and insert them into database
     */

    public void process() {

        String pol = Objects.requireNonNull(binding.tfPol.getEditText()).getText().toString();
        String pod = Objects.requireNonNull(binding.tfPod.getEditText()).getText().toString();
        String of20 = Objects.requireNonNull(binding.tfOf20.getEditText()).getText().toString();
        String of40 = Objects.requireNonNull(binding.tfOf40.getEditText()).getText().toString();
        String surcharge = Objects.requireNonNull(binding.tfSurcharge.getEditText()).getText().toString();
        String totalFreight = Objects.requireNonNull(binding.tfTotalFreight.getEditText()).getText().toString();
        String carrier = Objects.requireNonNull(binding.tfCarrier.getEditText()).getText().toString();
        String schedule = Objects.requireNonNull(binding.tfSchedule.getEditText()).getText().toString();
        String transit = Objects.requireNonNull(binding.tfTransitTime.getEditText()).getText().toString();
        String free = Objects.requireNonNull(binding.tfFreeTime.getEditText()).getText().toString();
        String valid = Objects.requireNonNull(binding.tfValid.getEditText()).getText().toString();
        String note = Objects.requireNonNull(binding.tfNote.getEditText()).getText().toString();

        ImportService importService = APIClient.getClient(Constants.URL_API).create(ImportService.class);

        Call<Import> call = importService.addData(pol, pod, of20, of40, surcharge, totalFreight, carrier, schedule, transit, free, valid, note, listStr[0],
                listStr[1], listStr[2]);

        call.enqueue(new Callback<Import>() {
            @Override
            public void onResponse(@NonNull Call<Import> call, @NonNull Response<Import> response) {
                Toast.makeText(getContext(), response.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NonNull Call<Import> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Successful!", Toast.LENGTH_LONG).show();
            }
        });
    }

}
