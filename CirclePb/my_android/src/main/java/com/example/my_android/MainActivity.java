package com.example.my_android;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.common.BaseActivity;
import com.example.my_android.ui.main.fragment.MainFragment;
import com.example.my_android.ui.main.fragment.MeFragment;
import com.example.my_android.ui.main.fragment.MessageFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    private FragmentTransaction mTransaction;
    private List<Fragment> mFragments;

    @Override
    protected void initData() {


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        mFragments = new ArrayList<>();
        mFragments.add(MainFragment.newInstance());
        mFragments.add(MessageFragment.newInstance());
        mFragments.add(MeFragment.newInstance());

        switchFragment(0);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                switchFragment(0);
                return true;
            case R.id.navigation_dashboard:
                switchFragment(1);
                return true;
            case R.id.navigation_notifications:
                switchFragment(2);
                return true;
        }
        return false;

    }

    public void switchFragment(int position) {

//        Intent intent = new Intent();
//        Bundle bundle = new Bundle();
//        bundle.putString("key", "value");
//        intent.putExtras(intent);
//        intent.putExtra("key","value");
//        startActivity(intent);


        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, mFragments.get(position))
                .addToBackStack(null)
                .commit();
    }


}
