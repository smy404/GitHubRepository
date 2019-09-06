package com.example.myrepository.mvp.ui.fragment.home;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.myrepository.R;
import com.example.myrepository.adapter.HomeFragmentAdapter2;
import com.example.myrepository.adapter.HomeFragmentAdapter3;
import com.example.myrepository.mvp.base.BaseLoadingFragment;
import com.example.myrepository.mvp.model.entity.MovieSubjects;

import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseLoadingFragment<HomePresenter> implements HomeContract.View {

    @BindView(R.id.recycleView)
    RecyclerView mRecycleView;
    @BindView(R.id.normalView)
    public SwipeRefreshLayout mNormalView;

    private HomePresenter mPresenter;
    private HomeFragmentAdapter2 mAdapter;
    private List<MovieSubjects> mList;

    @Override
    protected void inject() {


        //        mAdapter = new HomeFragmentAdapter2(getActivity());
        //        mRecycleView.setAdapter(mAdapter);


        mAdapter = new HomeFragmentAdapter2(getActivity());
        mRecycleView.setAdapter(mAdapter);
        mRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    protected void initView() {
        super.initView();

        initRefreshView();
    }

    @Override
    protected void loadData() {
        mPresenter.loadTheaters();
        //mPresenter.loadComingSoon();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomePresenter getPresenter() {
        mPresenter = new HomePresenter();
        return mPresenter;
    }


    @Override
    public void showTheaters(List<MovieSubjects> subjects) {
        initRecycleView(subjects);
    }


    @Override
    public void showComingSoon(List<MovieSubjects> subjects) {
        initRecycleView(subjects);
    }


    @Override
    public void showAllData(List<Object> list) {


        if (isRefresh) {

            mNormalView.setRefreshing(false);
        }

        mAdapter.setData(list);
    }


    private void initRecycleView(List<MovieSubjects> subjects) {


    }

    private boolean isRefresh = false;

    private void initRefreshView() {

        mNormalView.setColorSchemeResources(R.color.color_red);

        mNormalView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                isRefresh = true;

                mPresenter.loadRefreshAndMore();

            }
        });


    }
}
