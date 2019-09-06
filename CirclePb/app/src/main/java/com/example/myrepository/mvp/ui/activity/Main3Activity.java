package com.example.myrepository.mvp.ui.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.myrepository.R;
import com.example.myrepository.mvp.base.mvp.BaseMvpActivity;
import com.example.myrepository.mvp.ui.fragment.home.HomeFragment;
import com.example.myrepository.mvp.ui.fragment.me.MeFragment;
import com.example.myrepository.mvp.ui.fragment.project.ProjectFragment;
import com.example.myrepository.mvp.ui.fragment.system.SystemFragment;
import com.example.myrepository.mvp.ui.fragment.wx.WxFragment;
import com.example.myrepository.utils.LogUtil;

import butterknife.BindView;

public class Main3Activity extends BaseMvpActivity<MainPresenter> implements MainContract.View {


    @BindView(R.id.BottomBnv)
    BottomNavigationView mBottomBnv;
    private int mPreFragmentPosition = 0;
    Fragment[] mFragments = new Fragment[5];
    private MainPresenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        if (savedInstanceState == null) {
            mFragments[0] = new HomeFragment();
            mFragments[1] = new SystemFragment();
            mFragments[2] = new WxFragment();
            mFragments[3] = new ProjectFragment();
            mFragments[4] = new MeFragment();

            loadMultipleFragment(R.id.frameContain, 0, mFragments);
        } else {
            //            //屏幕翻转后回到当前页面，夜间模式开启后重新登录app
            mFragments[0] = findFragmentByTag(HomeFragment.class.getName());
            mFragments[1] = findFragmentByTag(SystemFragment.class.getName());
            mFragments[2] = findFragmentByTag(WxFragment.class.getName());
            mFragments[3] = findFragmentByTag(ProjectFragment.class.getName());
            mFragments[4] = findFragmentByTag(MeFragment.class.getName());
            mBottomBnv.setSelectedItemId(getSelectedItemId(getNavCurrentItem()));
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main3;
    }

    @Override
    protected void inject() {

    }

    @Override
    protected void initData() {
        initBottomNavigationView();
    }

    @Override
    protected MainPresenter getPresenter() {
        mPresenter = new MainPresenter();
        return mPresenter;
    }

    @Override
    public void showVersionUpdateDialog(String versionDetail) {

    }

    @Override
    public void downloadApk() {

//        startActivity();
//        startActivityForResult();
    }

    @Override
    public void setApkUrl(String apkUrl) {

    }

    private int getSelectedItemId(int position) {

        switch (position) {
            case 0:
                return R.id.homeItem;
            case 1:
                return R.id.systemItem; // Id:2131230825
            case 2:
                return R.id.wxItem;
            case 3:
                return R.id.projectItem;
            case 4:
                return R.id.personItem;
            default:
                break;
        }
        return 0;

    }

    private Integer getNavCurrentItem() {
        LogUtil.d("显示Pre：" + mPreFragmentPosition);
        SharedPreferences sp = getSharedPreferences("cache", Context.MODE_PRIVATE);
        return sp.getInt("prePosition", mPreFragmentPosition);
    }

    private Fragment findFragmentByTag(String tag) {
        return getSupportFragmentManager().findFragmentByTag(tag);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        SharedPreferences sp = getSharedPreferences("cache", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt("prePosition", mPreFragmentPosition);
        edit.apply();

    }

    private void initBottomNavigationView() {


        mBottomBnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.homeItem:
                        showAndHideFragment(mFragments[0], mFragments[mPreFragmentPosition]);
                        mPreFragmentPosition = 0;
                        //                        setStatusBarColor();
                        LogUtil.d(LogUtil.TAG, "home");
                        break;
                    case R.id.systemItem:
                        LogUtil.d(LogUtil.TAG, "system");
                        showAndHideFragment(mFragments[1], mFragments[mPreFragmentPosition]);
                        mPreFragmentPosition = 1;
                        //                        setStatusBarColor();
                        break;
                    case R.id.wxItem:
                        showAndHideFragment(mFragments[2], mFragments[mPreFragmentPosition]);
                        mPreFragmentPosition = 2;
                        //                        setStatusBarColor();
                        LogUtil.d(LogUtil.TAG, "wx");
                        break;
                    case R.id.projectItem:
                        showAndHideFragment(mFragments[3], mFragments[mPreFragmentPosition]);
                        mPreFragmentPosition = 3;
                        //                        setStatusBarColor();
                        LogUtil.d(LogUtil.TAG, "project");
                        break;
                    case R.id.personItem:
                        showAndHideFragment(mFragments[4], mFragments[mPreFragmentPosition]);
                        mPreFragmentPosition = 4;
                        //                        StatusBarUtil.immersiveInImage(this);
                        LogUtil.d(LogUtil.TAG, "person");
                        break;
                    default:
                        break;
                }
                return true;

            }
        });


    }

    private void showAndHideFragment(Fragment show, Fragment hide) {
        LogUtil.d(LogUtil.TAG, "show:" + show);
        LogUtil.d(LogUtil.TAG, "hide:" + hide);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (show != hide) {
            transaction.show(show).hide(hide).commitAllowingStateLoss();
        }

    }

    private void loadMultipleFragment(int containerId, int showFragment, Fragment... fragments) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        for (int i = 0; i < fragments.length; i++) {
            Fragment fragment = fragments[i];
            //最后一个参数为tag,add的时候添加一个tag来可以通过findFragmentByTag来查找fragment的实例
            transaction.add(containerId, fragment, fragment.getClass().getName());

            // 隐藏除了第 0 个之外的所有 Fragment
            if (i != showFragment) {
                transaction.hide(fragment);
            }
        }
        transaction.commitAllowingStateLoss();

    }


}
