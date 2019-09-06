package com.example.myrepository.mvp.ui.activity;

import com.example.myrepository.mvp.base.mvp.BaseView;
import com.example.myrepository.mvp.base.mvp.IPresenter;

public interface MainContract {

    interface View extends BaseView {
        void showVersionUpdateDialog(String versionDetail);         // 显示
        void downloadApk();                                         // 下载apk
        void setApkUrl(String apkUrl);                              // 设置新版本的下载地址
    }

    interface Presenter extends IPresenter<View> {
        void checkVersion(String currentVersion);                   // 检查版本更新
        void setNavCurrentItem(int position);                       // 保存页面状态，主要用于屏幕翻转
        int getNavCurrentItem();                                    // 获取页面状态
    }

}
