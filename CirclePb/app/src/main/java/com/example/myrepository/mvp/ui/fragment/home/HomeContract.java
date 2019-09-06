package com.example.myrepository.mvp.ui.fragment.home;

import com.example.myrepository.mvp.base.mvp.BaseView;
import com.example.myrepository.mvp.base.mvp.IPresenter;
import com.example.myrepository.mvp.model.entity.MovieResponse;
import com.example.myrepository.mvp.model.entity.MovieSubjects;

import java.util.List;

public interface HomeContract {
    interface View extends BaseView {
        void showTheaters(List<MovieSubjects> subjects );
        void showComingSoon(List<MovieSubjects> subjects );

        void showAllData(List<Object> list);
    }


    interface Presenter extends IPresenter<View> {
        void loadTheaters();
        void loadComingSoon();
        void loadRefreshAndMore();
    }

}
