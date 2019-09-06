package com.example.myrepository.fragment;


import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.myrepository.BaseFragment;
import com.example.myrepository.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**

 */
public class Fragment_8 extends BaseFragment {

    private static final String TAG = Fragment_8.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    Unbinder unbinder;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    Unbinder unbinder1;
    private int mOffset = 0;
    private int mScrollY = 0;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.iv_header)
    ImageView ivHeader;
    static Fragment_8 fragment_8;

    public Fragment_8() {
        // Required empty public constructor
    }

    public static Fragment_8 newInstance() {
        Bundle args = new Bundle();
        fragment_8 = new Fragment_8();
        fragment_8.setArguments(args);
        return fragment_8;
    }


    @Override
    protected void init() {

        initToolbar();

        initView();
    }

    private void initToolbar() {

        toolbar.setTitle("标题");
    }

    private void initView() {


        refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {

            @Override
            public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight) {


                ivHeader.setTranslationY(offset / 2);
                // ivHeader.setTranslationY(mOffset - mScrollY);

                // toolbar.setAlpha((1 - Math.min(percent, 1)));

                linearLayout.setAlpha((1 - Math.min(percent, 1)));
                Log.i(TAG, "onHeaderMoving: header=" + header +
                        "，isDragging=" + isDragging +
                        "，percent=" + percent +
                        "，offset=" + offset +
                        "，headerHeight=" + headerHeight +
                        "，maxDragHeight=" + maxDragHeight
                );

            }

            @Override
            public void onFooterMoving(RefreshFooter footer, boolean isDragging, float percent, int offset, int footerHeight, int maxDragHeight) {

                //                mOffset = offset / 2;
                //                ivHeader.setTranslationY(mOffset - mScrollY);

                Log.i(TAG, "onFooterMoving: mOffset=" + mOffset);
            }


        });
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_8;
    }


}
