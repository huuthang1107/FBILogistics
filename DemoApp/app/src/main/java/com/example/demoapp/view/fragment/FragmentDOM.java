package com.example.demoapp.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.demoapp.databinding.FragmentDomBinding;

public class FragmentDOM extends Fragment {
    FragmentDomBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDomBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }
}
