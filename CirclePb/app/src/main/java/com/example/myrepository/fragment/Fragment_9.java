package com.example.myrepository.fragment;


import android.os.Bundle;

import com.example.myrepository.BaseFragment;
import com.example.myrepository.R;

/**

 */
public class Fragment_9 extends BaseFragment {

    static Fragment_9 fragment_9;

    public Fragment_9() {
        // Required empty public constructor
    }

    public static Fragment_9 newInstance() {
        Bundle args = new Bundle();
        fragment_9 = new Fragment_9();
        fragment_9.setArguments(args);
        return fragment_9;
    }


    @Override
    protected void init() {

    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_9;
    }

}
