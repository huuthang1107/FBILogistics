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
import androidx.lifecycle.ViewModelProvider;

import com.example.demoapp.R;
import com.example.demoapp.adapter.PriceListAdapter;
import com.example.demoapp.databinding.FragmentDialogInsertBinding;
import com.example.demoapp.model.DetailsPojoFcl;
import com.example.demoapp.model.Fcl;
import com.example.demoapp.services.FCLService;
import com.example.demoapp.utilities.APIClient;
import com.example.demoapp.utilities.Constants;
import com.example.demoapp.viewmodel.FclViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertFclDialog extends DialogFragment implements View.OnClickListener {

    private final String[] itemsType = {"GP", "FR", "RF", "OT", "HC"};

    private final String[] itemsMonth = {"Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7",
            "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"};

    private final String[] itemsContinent = {"Asia", "Europe", "America", "Africa", "Australia"};

    private final String[] listStr = new String[3];

    private FragmentDialogInsertBinding binding;

    public static InsertFclDialog insertDialog() {
        return new InsertFclDialog();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentDialogInsertBinding.inflate(inflater, container, false);

        View view = binding.getRoot();

        initView();

        return view;
    }

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
                dismiss();
                break;
            case R.id.btn_function_cancel:
                dismiss();
                break;
        }
    }


    public void process() {

        String pol = binding.tfPol.getEditText().getText().toString();
        String pod = binding.tfPod.getEditText().getText().toString();
        String of20 = binding.tfOf20.getEditText().getText().toString();
        String of40 = binding.tfOf40.getEditText().getText().toString();
        String su20 = binding.tfSu20.getEditText().getText().toString();
        String su40 = binding.tfSu40.getEditText().getText().toString();
        String line = binding.tfLines.getEditText().getText().toString();
        String notes = binding.tfNotes.getEditText().getText().toString();
        String valid = binding.tfValid.getEditText().getText().toString();
        String note2 = binding.tfNotes2.getEditText().getText().toString();


        FCLService fclService = APIClient.getClient(Constants.URL_API).create(FCLService.class);

        Call<Fcl> call = fclService.addData(pol, pod, of20, of40, su20, su40, line, notes,
                valid, note2, listStr[1], listStr[0], listStr[2]);

        call.enqueue(new Callback<Fcl>() {
            @Override
            public void onResponse(@NonNull Call<Fcl> call, @NonNull Response<Fcl> response) {
                Toast.makeText(getContext(), response.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NonNull Call<Fcl> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "Successful!", Toast.LENGTH_LONG).show();
            }
        });
    }

}
