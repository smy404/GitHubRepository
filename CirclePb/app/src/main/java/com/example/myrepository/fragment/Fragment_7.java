package com.example.myrepository.fragment;


import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;

import com.example.myrepository.BaseFragment;
import com.example.myrepository.MainActivity;
import com.example.myrepository.R;
import com.example.myrepository.mvp.ui.view.JudgeNestedScrollView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;

import net.lucode.hackware.magicindicator.MagicIndicator;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**

 */
public class Fragment_7 extends BaseFragment {
    public static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_menu)
    ImageView ivMenu;

    @BindView(R.id.iv_header)
    ImageView ivHeader;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.scrollView)
    JudgeNestedScrollView scrollView;
    @BindView(R.id.buttonBarLayout)
    ButtonBarLayout buttonBarLayout;
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.magic_indicator_title)
    MagicIndicator magicIndicatorTitle;
    int toolBarPositionY = 0;
    private int mOffset = 0;
    private int mScrollY = 0;
    private String[] mTitles = new String[]{"动态", "文章", "问答"};
    private List<String> mDataList = Arrays.asList(mTitles);

    static Fragment_7 fragment_7;

    public Fragment_7() {
        // Required empty public constructor
    }

    public static Fragment_7 newInstance() {
        Bundle args = new Bundle();
        fragment_7 = new Fragment_7();
        fragment_7.setArguments(args);
        return fragment_7;
    }


    @Override
    protected void init() {

        initView();
    }

    private void initView() {


        refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {

            @Override
            public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight) {

                //                mOffset = offset / 2;
                //                ivHeader.setTranslationY(mOffset - mScrollY);

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

                mOffset = offset / 2;
                ivHeader.setTranslationY(mOffset - mScrollY);
            }

            @Override
            public void onHeaderReleased(RefreshHeader header, int headerHeight, int maxDragHeight) {


            }
        });
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_7;
    }

}
