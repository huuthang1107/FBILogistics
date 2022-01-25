package com.example.demoapp.view.dialog.dom;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.demoapp.R;
import com.example.demoapp.databinding.FragmentDialogDomExportDetailBinding;
import com.example.demoapp.databinding.FragmentDomExportBinding;

public class DialogDomExportDetail extends DialogFragment {

    private FragmentDialogDomExportDetailBinding binding;

    public DialogDomExportDetail() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DialogDomExportDetail getInstance() {
        return new DialogDomExportDetail();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDialogDomExportDetailBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }
}