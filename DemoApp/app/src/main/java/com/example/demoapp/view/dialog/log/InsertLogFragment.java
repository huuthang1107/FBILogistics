package com.example.demoapp.view.dialog.log;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.demoapp.R;
import com.example.demoapp.databinding.FragmentInsertLogBinding;
import com.example.demoapp.model.Log;
import com.example.demoapp.utilities.Constants;
import com.example.demoapp.viewmodel.CommunicateViewModel;
import com.example.demoapp.viewmodel.LogViewModel;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertLogFragment extends DialogFragment implements View.OnClickListener{

    private static final int MY_REQUEST_CODE = 10;

    private final String[] itemsImportOrExport = {"Nhập Khẩu", "Xuất Khẩu"};

    private final String[] listStr = new String[2];

    public static InsertLogFragment insertDiaLogLog(){
        return new InsertLogFragment();

    }
    private ArrayAdapter<String> adapterItemsMonth, adapterItemsImportAndExport;

    private FragmentInsertLogBinding logBinding;

    private LogViewModel mLogViewModel;

    private Bitmap bitmap;

    private CommunicateViewModel mCommunicateViewModel;

    public static final String TAG = InsertLogFragment.class.getName();

    ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    android.util.Log.e(TAG, "onActivityResult");
                    if(result.getResultCode() == getActivity().RESULT_OK){
                        Intent data = result.getData();
                        if(data == null){
                            return;
                        }
                        Uri uri = data.getData();
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                            logBinding.ivAddLog.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        logBinding = FragmentInsertLogBinding.inflate(inflater,container, false);
        View view = logBinding.getRoot();

        mLogViewModel = new ViewModelProvider(this).get(LogViewModel.class);
        mCommunicateViewModel = new ViewModelProvider(getActivity()).get(CommunicateViewModel.class);
        initView();
        return view;
    }

    private void initView() {
        adapterItemsMonth = new ArrayAdapter<String>(getContext(), R.layout.dropdown_item, Constants.ITEMS_MONTH);
        adapterItemsImportAndExport = new ArrayAdapter<String>(getContext(), R.layout.dropdown_item, itemsImportOrExport);

        logBinding.insertAutoMonth.setAdapter(adapterItemsMonth);
        logBinding.insertAutoContinent.setAdapter(adapterItemsImportAndExport);

        logBinding.btnFunctionAdd.setOnClickListener(this);
        logBinding.btnFunctionCancel.setOnClickListener(this);
        logBinding.btnDinhkemhinhanh.setOnClickListener(this);

        logBinding.insertAutoMonth.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                listStr[0] = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getContext(), listStr[0], Toast.LENGTH_LONG).show();
            }
        });

        logBinding.insertAutoContinent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                insertLog();

                break;
            case R.id.btn_function_cancel:
                dismiss();
                break;
            case R.id.btn_dinhkemhinhanh:
                onClickRequestPermission();
        }
    }

    private void onClickRequestPermission() {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            openGallery();
            return;
        }
        if(getContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            openGallery();
        } else{
            String [] permission = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
            requestPermissions(permission, MY_REQUEST_CODE);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == MY_REQUEST_CODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openGallery();
            }
        }
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);
        mActivityResultLauncher.launch(Intent.createChooser(intent,"Select Picture"));
    }


    private void insertLog() {
        String strTenHang = logBinding.tfTenhang.getEditText().getText().toString();
        String strhscode = logBinding.tfHscode.getEditText().getText().toString();
        String strcongdung = logBinding.tfCongdung.getEditText().getText().toString();
        String strhinhanh = logBinding.tfHinhanh.getEditText().getText().toString();
        String strcangdi = logBinding.tfCangdi.getEditText().getText().toString();
        String strcangden = logBinding.tfCangden.getEditText().getText().toString();
        String strloaihang = logBinding.tfLoaihang.getEditText().getText().toString();
        String strsoluongcuthe = logBinding.tfSoluongcuthe.getEditText().getText().toString();
        String stryeucaudacbiet = logBinding.tfYeucaudacbiet.getEditText().getText().toString();
        String strValid = logBinding.tfValid.getEditText().getText().toString();

        mCommunicateViewModel.makeChanges();
        Call<Log> call = mLogViewModel.insertLog(strTenHang, strhscode, strcongdung, strhinhanh,
                strcangdi, strcangden, strloaihang, strsoluongcuthe, stryeucaudacbiet, strValid,
                listStr[0], listStr[1]);
        call.enqueue(new Callback<Log>() {
            @Override
            public void onResponse(Call<Log> call, Response<Log> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getContext(), "Created Successful!!", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<Log> call, Throwable t) {

            }
        });
        resetEditText();

    }
    public void resetEditText(){
        Objects.requireNonNull(logBinding.tfTenhang.getEditText()).setText("");
        Objects.requireNonNull(logBinding.tfHscode.getEditText()).setText("");
        Objects.requireNonNull(logBinding.tfCongdung.getEditText()).setText("");
        Objects.requireNonNull(logBinding.tfHinhanh.getEditText()).setText("");
        Objects.requireNonNull(logBinding.tfCangdi.getEditText()).setText("");
        Objects.requireNonNull(logBinding.tfCangden.getEditText()).setText("");
        Objects.requireNonNull(logBinding.tfLoaihang.getEditText()).setText("");
        Objects.requireNonNull(logBinding.tfSoluongcuthe.getEditText()).setText("");
        Objects.requireNonNull(logBinding.tfYeucaudacbiet.getEditText()).setText("");
        Objects.requireNonNull(logBinding.tfValid.getEditText()).setText("");

    }
    private void uploadImage(){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,75, byteArrayOutputStream);

        byte [] imageInByte = byteArrayOutputStream.toByteArray();

        String encodeImage = Base64.encodeToString(imageInByte, Base64.DEFAULT);

        Toast.makeText(getContext(), encodeImage,Toast.LENGTH_LONG).show();

    }
}