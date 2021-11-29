package com.example.demoapp.table_function;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.demoapp.R;
import com.example.demoapp.professional.ActivityPriceList;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentDialogInsert extends DialogFragment implements View.OnClickListener {
    // dropdown menu
    private String[] items = {"GP", "FR", "RF", "OT", "HC"};
    private AutoCompleteTextView autoCompleteTextView;
    private ArrayAdapter<String> adapterItems;

    private String selectItem;

    // button
    private Button btnAdd, btnCancel;

    // Edittext
    private TextInputLayout et_stt, et_pol, et_pod, et_of20, et_of40, et_su20, et_su40,
            et_lines, et_notes1, et_valid, et_notes2;

    // URL server
    String ServerURL = "http://192.168.1.6/database/";

    public static FragmentDialogInsert insertDialog() {
        return new FragmentDialogInsert();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_insert, container, false);

        // Dropdown menu
        autoCompleteTextView = view.findViewById(R.id.typeof_auto_complete);

        adapterItems = new ArrayAdapter<String>(view.getContext(), R.layout.dropdown_item, items);
        autoCompleteTextView.setAdapter(adapterItems);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem = parent.getItemAtPosition(position).toString();
            }
        });
        // Button
        btnAdd = view.findViewById(R.id.btn_function_add);
        btnCancel = view.findViewById(R.id.btn_function_cancel);

        // Edit text
        et_stt = view.findViewById(R.id.tf_stt);
        et_pol = view.findViewById(R.id.tf_pol);
        et_pod = view.findViewById(R.id.tf_pod);
        et_of20 = view.findViewById(R.id.tf_of20);
        et_of40 = view.findViewById(R.id.tf_of40);
        et_su20 = view.findViewById(R.id.tf_su20);
        et_su40 = view.findViewById(R.id.tf_su40);
        et_lines = view.findViewById(R.id.tf_lines);
        et_notes1 = view.findViewById(R.id.tf_notes);
        et_valid = view.findViewById(R.id.tf_valid);
        et_notes2 = view.findViewById(R.id.tf_notes2);

        // Set listener for buttons
        btnAdd.setOnClickListener(this);
        btnCancel.setOnClickListener(this);

        // cannot touch to screen to close this dialog
        setCancelable(false);

        return view;
    }

    // add a row to table
    public void insertData(String stt, String pol, String pod, String of20, String of40,
                           String su20, String su40, String lines, String notes1, String valid,
                           String notes2, String month
            , String type) {


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_function_add:
                process();
                resetEditText();
                break;
            case R.id.btn_function_cancel:
                dismiss();
                break;
        }
    }

    public void process() {
        String strStt = String.valueOf(Objects.requireNonNull(et_stt.getEditText()).getText());
        String strPol = String.valueOf(Objects.requireNonNull(et_pol.getEditText()).getText());
        String strPod = String.valueOf(Objects.requireNonNull(et_pod.getEditText()).getText());
        String strOf20 = String.valueOf(Objects.requireNonNull(et_of20.getEditText()).getText());
        String strOf40 = String.valueOf(Objects.requireNonNull(et_of40.getEditText()).getText());
        String strSu20 = String.valueOf(Objects.requireNonNull(et_su20.getEditText()).getText());
        String strSu40 = String.valueOf(Objects.requireNonNull(et_su40.getEditText()).getText());
        String strLines = String.valueOf(Objects.requireNonNull(et_lines.getEditText()).getText());
        String strNotes = String.valueOf(Objects.requireNonNull(et_notes1.getEditText()).getText());
        String strValid = String.valueOf(Objects.requireNonNull(et_valid.getEditText()).getText());
        String strNotes2 = String.valueOf(Objects.requireNonNull(et_notes2.getEditText()).getText());
        String strMonth = "1";
        String strType = selectItem;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ServerURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyAPI myAPI = retrofit.create(MyAPI.class);

        Call<Model> call = myAPI.addData(strStt, strPol, strPod, strOf20, strOf40, strSu20, strSu40,
                strLines, strNotes, strValid, strNotes2, strMonth, strType);

        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(@NonNull Call<Model> call, @NonNull Response<Model> response) {
                Toast.makeText(getContext(), response.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(@NonNull Call<Model> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void resetEditText(){
        Objects.requireNonNull(et_stt.getEditText()).setText("");
        Objects.requireNonNull(et_pol.getEditText()).setText("");
        Objects.requireNonNull(et_pod.getEditText()).setText("");
        Objects.requireNonNull(et_of20.getEditText()).setText("");
        Objects.requireNonNull(et_of40.getEditText()).setText("");
        Objects.requireNonNull(et_su20.getEditText()).setText("");
        Objects.requireNonNull(et_su40.getEditText()).setText("");
        Objects.requireNonNull(et_lines.getEditText()).setText("");
        Objects.requireNonNull(et_notes1.getEditText()).setText("");
        Objects.requireNonNull(et_valid.getEditText()).setText("");
        Objects.requireNonNull(et_notes2.getEditText()).setText("");
    }
}
