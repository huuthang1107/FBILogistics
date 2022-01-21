package com.example.demoapp.view.activity.dom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.demoapp.R;
import com.example.demoapp.databinding.ActivityDomBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class DomActivity extends AppCompatActivity {

    private ActivityDomBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();

    }

    public void initView() {
        BottomNavigationView navView = findViewById(R.id.dom_nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_dom);

        NavigationUI.setupWithNavController(binding.domNavView, navController);

        navView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.navigation_dom:

                        return true;
                }
                return true;
            }
        });

    }


}