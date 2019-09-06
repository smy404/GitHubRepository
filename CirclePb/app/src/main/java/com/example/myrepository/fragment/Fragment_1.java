package com.example.myrepository.fragment;


import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myrepository.BaseFragment;
import com.example.myrepository.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.content.ContentValues.TAG;

/**

 */
public class Fragment_1 extends BaseFragment {

    public static Fragment_1 mFragment_1;
    @BindView(R.id.ProgressView)
    ProgressView mProgressView;

    public Fragment_1() {
        // Required empty public constructor
    }

    public static Fragment_1 newInstance() {
        Bundle args = new Bundle();
        mFragment_1 = new Fragment_1();
        mFragment_1.setArguments(args);
        return mFragment_1;
    }

    @Override
    protected void init() {


//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                for (int i = 0; i <= 100; i++) {
//                    float percent = i / 100f;
//                    SystemClock.sleep(100);
//
//                    Log.i(TAG, "run: " + percent);
//                    mProgressView.setPercent(percent);
//                }
//            }
//        }).start();

        //        int percent = 0;

    }


    @Override
    protected int setLayoutId() {
        return R.layout.fragment_1;
    }


}
