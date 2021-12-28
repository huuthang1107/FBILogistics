package com.example.demoapp.view.dialog;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.demoapp.R;
import com.example.demoapp.databinding.FragmentDialogInsertAirBinding;
import com.example.demoapp.model.Air;
import com.example.demoapp.viewmodel.AirViewModel;
import com.example.demoapp.viewmodel.CommunicateViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class InsertAirDialog extends DialogFragment implements  View.OnClickListener {

    private final String[] itemsMonth = {"Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5", "Tháng 6", "Tháng 7",
            "Tháng 8", "Tháng 9", "Tháng 10", "Tháng 11", "Tháng 12"};

    private final String[] itemsContinent = {"Asia", "Europe", "America", "Africa", "Australia"};

    private final String[] listStr = new String[2];
    private FragmentDialogInsertAirBinding insertAirBinding;
    private ArrayAdapter<String>  adapterItemsMonth, adapterItemsContinent;

    private AirViewModel mAirViewModel;
    private List<Air> airList = new ArrayList<>();
    private CommunicateViewModel mCommunicateViewModel;

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

        mAirViewModel = new ViewModelProvider(this).get(AirViewModel.class);
        mCommunicateViewModel = new ViewModelProvider(getActivity()).get(CommunicateViewModel.class);

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
        String stAol = insertAirBinding.tfPol.getEditText().getText().toString();
        String stAod = insertAirBinding.tfPod.getEditText().getText().toString();
        String stDim = insertAirBinding.tfDim.getEditText().getText().toString();
        String stGross = insertAirBinding.tfGross.getEditText().getText().toString();
        String stType = insertAirBinding.tfTypeofcargo.getEditText().getText().toString();
        String stFreight = insertAirBinding.tfAirfreight.getEditText().getText().toString();
        String stSurcharge = insertAirBinding.tfSurcharge.getEditText().getText().toString();
        String stLines = insertAirBinding.tfAirlines.getEditText().getText().toString();
        String stSchedule = insertAirBinding.tfSchedule.getEditText().getText().toString();
        String stTransittime = insertAirBinding.tfTfTransitTime.getEditText().getText().toString();
        String stValid = insertAirBinding.tfValid.getEditText().getText().toString();
        String stNote = insertAirBinding.tfNotes.getEditText().getText().toString();

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Contants.URL_API)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        AIRService airService = retrofit.create(AIRService.class);
        mCommunicateViewModel.makeChanges();
        Call<Air> call = mAirViewModel.insertAir(stAol,stAod, stDim, stGross, stType, stFreight,
                stSurcharge, stLines, stSchedule, stTransittime, stValid, stNote, listStr[0], listStr[1]);
        call.enqueue(new Callback<Air>() {
            @Override
            public void onResponse(Call<Air> call, Response<Air> response) {
                Toast.makeText(getContext(),response.toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Air> call, Throwable t) {
                Toast.makeText(getContext(),"Insert AIR Sucessfull", Toast.LENGTH_SHORT).show();

            }
        });
        resetEditText();
    }

    public void resetEditText(){
        Objects.requireNonNull(insertAirBinding.tfPol.getEditText()).setText("");
        Objects.requireNonNull(insertAirBinding.tfPod.getEditText()).setText("");
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