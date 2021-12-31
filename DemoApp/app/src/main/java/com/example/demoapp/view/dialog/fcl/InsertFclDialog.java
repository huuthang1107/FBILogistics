package com.example.demoapp.view.dialog.fcl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.example.demoapp.databinding.FragmentDialogInsertBinding;
import com.example.demoapp.model.Fcl;
import com.example.demoapp.utilities.Constants;
import com.example.demoapp.viewmodel.CommunicateViewModel;
import com.example.demoapp.viewmodel.FclViewModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertFclDialog extends DialogFragment implements View.OnClickListener {

    private final String[] listStr = new String[3];

    private FragmentDialogInsertBinding binding;

    private FclViewModel mFclViewModel;
    private CommunicateViewModel mCommunicateViewModel;


    public static InsertFclDialog insertDialog() {
        return new InsertFclDialog();
    }

    /**
     * This method will set a view for insert dialog
     *
     * @param inflater           inflater
     * @param container          container
     * @param savedInstanceState save
     * @return view of this fragment dialog
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentDialogInsertBinding.inflate(inflater, container, false);

        View view = binding.getRoot();

        mFclViewModel = new ViewModelProvider(this).get(FclViewModel.class);
        mCommunicateViewModel = new ViewModelProvider(getActivity()).get(CommunicateViewModel.class);

        initView();

        return view;
    }

    /**
     * this method will init for all views and get a item of auto complete textview
     */
    public void initView() {

        // auto complete textview
        ArrayAdapter<String> adapterItemsType = new ArrayAdapter<String>(getContext(), R.layout.dropdown_item, Constants.ITEMS_FCL);
        ArrayAdapter<String> adapterItemsMonth = new ArrayAdapter<String>(getContext(), R.layout.dropdown_item, Constants.ITEMS_MONTH);
        ArrayAdapter<String> adapterItemsContinent = new ArrayAdapter<String>(getContext(), R.layout.dropdown_item, Constants.ITEMS_CONTINENT);

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
            }
        });

        binding.insertAutoMonth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listStr[1] = adapterView.getItemAtPosition(i).toString();
            }
        });

        binding.insertAutoContinent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listStr[2] = adapterView.getItemAtPosition(i).toString();
            }
        });

        setCancelable(false);

    }

    /**
     * This method set event for and and cancel buttons
     *
     * @param v view
     */
    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_function_add:
                if (isFilled()) {
                    insertData();
                    dismiss();
                }
                break;
            case R.id.btn_function_cancel:
                dismiss();
                break;
        }
    }

    /**
     * This method used to get data user typing and insert them into database
     */
    public void insertData() {

        String pol = Objects.requireNonNull(binding.tfPol.getEditText()).getText().toString();
        String pod = Objects.requireNonNull(binding.tfPod.getEditText()).getText().toString();
        String of20 = Objects.requireNonNull(binding.tfOf20.getEditText()).getText().toString();
        String of40 = Objects.requireNonNull(binding.tfOf40.getEditText()).getText().toString();
        String su20 = Objects.requireNonNull(binding.tfSu20.getEditText()).getText().toString();
        String su40 = Objects.requireNonNull(binding.tfSu40.getEditText()).getText().toString();
        String line = Objects.requireNonNull(binding.tfLines.getEditText()).getText().toString();
        String notes = Objects.requireNonNull(binding.tfNotes.getEditText()).getText().toString();
        String valid = Objects.requireNonNull(binding.tfValid.getEditText()).getText().toString();
        String note2 = Objects.requireNonNull(binding.tfNotes2.getEditText()).getText().toString();

        mCommunicateViewModel.makeChanges();
        Call<Fcl> call = mFclViewModel.insertFcl(pol, pod, of20, of40, su20, su40, line, notes, valid, note2, listStr[1], listStr[0], listStr[2], getCreatedDate());

        call.enqueue(new Callback<Fcl>() {
            @Override
            public void onResponse(@NonNull Call<Fcl> call, @NonNull Response<Fcl> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), "Created Successful!!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Fcl> call, @NonNull Throwable t) {

            }
        });
    }

    private String getCreatedDate() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

    /**
     *
     * @return false if the user does not select auto complete
     */

    public boolean isFilled() {
        boolean result = true;

        if (TextUtils.isEmpty(binding.insertAutoContainer.getText())) {
            result = false;
            binding.insertAutoContainer.setError(Constants.ERROR_AUTO_COMPLETE_TYPE);
        }

        if (TextUtils.isEmpty(binding.insertAutoMonth.getText())) {
            result = false;
            binding.insertAutoMonth.setError(Constants.ERROR_AUTO_COMPLETE_MONTH);
        }

        if (TextUtils.isEmpty(binding.insertAutoContinent.getText())) {
            result = false;
            binding.insertAutoContinent.setError(Constants.ERROR_AUTO_COMPLETE_CONTINENT);
        }

        return result;
    }

}
