package com.example.myrepository.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.myrepository.fragment.Fragment_1;
import com.example.myrepository.fragment.Fragment_2;
import com.example.myrepository.fragment.Fragment_3;

import java.util.ArrayList;
import java.util.List;

public class SystemFragmentAdapter<T> extends FragmentPagerAdapter {

    String[] str = {};

    private List<T> datas = new ArrayList<>();

    public SystemFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return Fragment_1.newInstance();

            case 1:
                return Fragment_2.newInstance();

            case 2:
                return Fragment_3.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    public void setData(List<T> list) {

        if (list != null) {
            datas.clear();
            datas.addAll(list);
        }
    }

}
