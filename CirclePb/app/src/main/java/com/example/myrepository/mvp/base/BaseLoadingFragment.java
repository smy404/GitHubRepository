package com.example.myrepository.mvp.base;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myrepository.R;
import com.example.myrepository.mvp.base.mvp.BaseMvpFragment;
import com.example.myrepository.mvp.base.mvp.IPresenter;
import com.example.myrepository.mvp.ui.view.Lead;
import com.example.myrepository.mvp.ui.view.LeadTextView;

public abstract class BaseLoadingFragment<T extends IPresenter> extends BaseMvpFragment<T> {

    //BaseLoadingState
    public static final int NORMAL_STATE = 0;
    public static final int LOADING_STATE = 1;
    public static final int ERROR_STATE = 2;

    private View mLoadingView;              // 加载布局
    private ViewGroup mNormalView;          // 正常布局
    private View mErrorView;                // 错误布局
        private LeadTextView mLoadingText;
        private Lead lead ;

    private int mCurrentState = NORMAL_STATE;
    private ProgressBar mLoadingView1;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getView() == null) return;
        mNormalView = view.findViewById(R.id.normalView);
        if (mNormalView == null) {
            throw new IllegalStateException("The subclass of BaseLoadFragment must contain a View it's id is named normal_view");
        }
        if (!(mNormalView.getParent() instanceof ViewGroup)) {
            throw new IllegalStateException("mNormalView's ParentView should be a ViewGroup");
        }
        ViewGroup parent = (ViewGroup) mNormalView.getParent();
        View.inflate(mActivity, R.layout.loading_view, parent);
        View.inflate(mActivity, R.layout.loading_error_view, parent);

        //loading
        mLoadingView = parent.findViewById(R.id.loadingView);
        mLoadingText = mLoadingView.findViewById(R.id.loadingText);



//        mLoadingView1 = mLoadingView.findViewById(R.id.loading);
        //        mLoadingText.setTypeface(TypefacesUtil.get(mActivity,"Satisfy-Regular.ttf"));
                lead = new Lead(1000);

        lead.start(mLoadingText);
        //error
        mErrorView = parent.findViewById(R.id.errorView);
        //重试
        TextView mErrorRetry = mErrorView.findViewById(R.id.retryTv);
        mErrorRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });


        mNormalView.setVisibility(View.VISIBLE);
        mLoadingView.setVisibility(View.GONE);
        mErrorView.setVisibility(View.GONE);

    }


    @Override
    public void showLoading() {
        if (mCurrentState == LOADING_STATE) return;
        hideCurrentViewByState();
        mCurrentState = LOADING_STATE;
        mLoadingView.setVisibility(View.VISIBLE);
        //        lead.start(mLoadingText);
    }

    @Override
    public void showNormalView() {
        if (mCurrentState == NORMAL_STATE) return;
        hideCurrentViewByState();
        mCurrentState = NORMAL_STATE;
        mNormalView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorView() {
        if (mCurrentState == ERROR_STATE) return;
        hideCurrentViewByState();
        mCurrentState = ERROR_STATE;
        mErrorView.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏当前布局根据mCurrentState
     */
    private void hideCurrentViewByState() {
        switch (mCurrentState) {
            case NORMAL_STATE:
                if (mNormalView == null) return;
                mNormalView.setVisibility(View.GONE);
                break;
            case LOADING_STATE:
                if (mLoadingView == null) return;
                //                lead.cancel();
                mLoadingView.setVisibility(View.GONE);
                break;
            case ERROR_STATE:
                if (mErrorView == null) return;
                mErrorView.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }
}
