package com.example.demoapp.professional;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.demoapp.R;
import com.example.demoapp.fragment_pro.FCLFragment;
import com.example.demoapp.fragment_pro.HomeFragment;
import com.example.demoapp.fragment_pro.LCLFragment;
import com.google.android.material.navigation.NavigationView;

public class ProActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private final static int FRAGMENT_HOME = 0;
    private final static int FRAGMENT_FCL = 1;
    private final static int FRAGMENT_LCL = 2;

    // check currently fragment
    private int mCurrentFragment = FRAGMENT_HOME;

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professional_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,mDrawerLayout,
                toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        // vao app la open fragment home
        replaceFragment(new HomeFragment());

        // checked fragment home
        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.nav_home){
            if(mCurrentFragment != FRAGMENT_HOME){
                replaceFragment(new HomeFragment());
                mCurrentFragment = FRAGMENT_HOME;
            }
        }else if(id == R.id.nav_fcl){
            if(mCurrentFragment != FRAGMENT_FCL){
                replaceFragment(new FCLFragment());
                mCurrentFragment = FRAGMENT_FCL;
            }

        }else if (id == R.id.nav_lcl){
            if(mCurrentFragment != FRAGMENT_LCL){
                replaceFragment(new LCLFragment());
                mCurrentFragment = FRAGMENT_LCL;
            }
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    // khi nhan back khong thoat app
    @Override
    public void onBackPressed() {
        if(mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame,fragment);
        transaction.commit();
    }
}