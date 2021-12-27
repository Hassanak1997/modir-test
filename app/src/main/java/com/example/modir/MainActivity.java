package com.example.modir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;
    UserFragment userFragment;
    RestFragment restFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = (FrameLayout) findViewById(R.id.frame_layout_main);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_main);

        userFragment = new UserFragment();
        restFragment = new RestFragment();

        setFragment(restFragment);
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {

            switch (menuItem.getItemId()){
                case R.id.requests_id:{
                    setFragment(restFragment);
                    return true; }
                case R.id.users_id:{
                    setFragment(userFragment);
                    return true; }
            }

            return false;
        });

    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_main,fragment);
        fragmentTransaction.commit();

    }
}