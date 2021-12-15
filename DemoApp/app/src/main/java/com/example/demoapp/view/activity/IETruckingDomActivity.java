package com.example.demoapp.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.demoapp.R;
import com.example.demoapp.databinding.ActivityIetruckingDomBinding;
import com.example.demoapp.view.fragment.DomExportFragment;
import com.example.demoapp.view.fragment.DomImportFragment;
import com.google.android.material.navigation.NavigationBarView;

public class IETruckingDomActivity extends AppCompatActivity {
    private ActivityIetruckingDomBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIetruckingDomBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(view);

        getSupportFragmentManager().beginTransaction().replace(R.id.main_container_dom, new DomExportFragment()).commit();

        binding.bottomNavigationDom.setSelectedItemId(R.id.nav_export_bottom);

        binding.bottomNavigationDom.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.nav_export_bottom:
                        fragment = new DomExportFragment();
                        break;
                    case R.id.nav_imp_bottom:
                        fragment = new DomImportFragment();
                        break;
                }
                assert fragment != null;
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container_dom,fragment).commit();
                return true;
            }
        });
    }
}