package com.example.myrepository.fragment;


import android.os.Bundle;

import com.example.myrepository.BaseFragment;
import com.example.myrepository.R;

/**

 */
public class Fragment_10 extends BaseFragment {

    static Fragment_10 fragment_10;

    public Fragment_10() {
        // Required empty public constructor
    }

    public static Fragment_10 newInstance() {
        Bundle args = new Bundle();
        fragment_10 = new Fragment_10();
        fragment_10.setArguments(args);
        return fragment_10;
    }


    @Override
    protected void init() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_10;
    }

}
