package com.example.demoapp.view.activity.dom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.demoapp.R;

import com.example.demoapp.databinding.ActivityIeDomBinding;
import com.example.demoapp.view.activity.pro.ProActivity;
import com.example.demoapp.view.fragment.dom.DomExportFragment;
import com.example.demoapp.view.fragment.dom.DomImportFragment;
import com.google.android.material.navigation.NavigationView;

public class IEDomActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ActivityIeDomBinding binding;

    private final static int FRAGMENT_HOME = 0;
    private final static int EXPORT_DOM = 1;
    private final static int IMPORT_DOM = 2;
    private int mCurrentFragment = EXPORT_DOM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityIeDomBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(view);

        setSupportActionBar(binding.toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,binding.drawerDom,
                binding.toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        binding.drawerDom.addDrawerListener(toggle);
        toggle.syncState();

        binding.navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new DomExportFragment());
        binding.toolbar.setTitle("HÀNG XUẤT");

        binding.navigationView.getMenu().findItem(R.id.nav_ex).setChecked(true);
    }



    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container_dom,fragment);
        transaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.nav_home){
            Intent intent = new Intent(this, ProActivity.class);
            startActivity(intent);
            finish();

        }else if(id == R.id.nav_ex){
            if(mCurrentFragment != EXPORT_DOM){
                replaceFragment(new DomExportFragment());
                mCurrentFragment = EXPORT_DOM;
                binding.toolbar.setTitle("HÀNG XUẤT");
                binding.navigationView.getMenu().findItem(R.id.nav_ex).setChecked(true);
                binding.navigationView.getMenu().findItem(R.id.nav_imp).setChecked(false);
            }

        }else if (id == R.id.nav_imp) {
            if (mCurrentFragment != IMPORT_DOM) {
                replaceFragment(new DomImportFragment());
                mCurrentFragment = IMPORT_DOM;
                binding.toolbar.setTitle("HÀNG NHẬP");
                binding.navigationView.getMenu().findItem(R.id.nav_ex).setChecked(false);
                binding.navigationView.getMenu().findItem(R.id.nav_imp).setChecked(true);
            }
        }
        binding.drawerDom.closeDrawer(GravityCompat.START);
        return true;
    }
}