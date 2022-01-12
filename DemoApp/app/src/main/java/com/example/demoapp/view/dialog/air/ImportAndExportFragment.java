package com.example.demoapp.view.dialog.air;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.demoapp.R;
import com.example.demoapp.databinding.FragmentImportAndExportBinding;


public class ImportAndExportFragment extends DialogFragment implements View.OnClickListener {

    private FragmentImportAndExportBinding mInImportAndExportBinding;

    public static ImportAndExportFragment insertImportAndExportDiaLogAIR() {
        return new ImportAndExportFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mInImportAndExportBinding = FragmentImportAndExportBinding.inflate(inflater, container, false);
        View view = mInImportAndExportBinding.getRoot();

        unit();
        return view;
    }

    private void unit() {
        mInImportAndExportBinding.btnExportAir.setOnClickListener(this);
        mInImportAndExportBinding.btnImportAir.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_export_air:
                DialogFragment dialogFragmentExport = InsertAirExportDialog.insertDiaLogAIR();
                dialogFragmentExport.show(getParentFragmentManager(), "Insert Dialog Air Export");
                break;

            case R.id.btn_import_air:
                DialogFragment dialogFragmentImport = InsertAirImportDialog.insertDiaLogAIRImport();
                dialogFragmentImport.show(getParentFragmentManager(),"Insert Dialog Air Import");
                break;
        }
    }
}