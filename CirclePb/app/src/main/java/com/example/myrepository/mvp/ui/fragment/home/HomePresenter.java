package com.example.myrepository.mvp.ui.fragment.home;


import com.example.myrepository.mvp.BaseObserver;
import com.example.myrepository.mvp.base.mvp.BasePresenter;
import com.example.myrepository.mvp.model.entity.MovieResponse;
import com.example.myrepository.mvp.model.entity.MovieSubjects;
import com.example.myrepository.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {


    private ArrayList<Object> mList;
    private ArrayList<MovieResponse<MovieSubjects>> mList2;

    public HomePresenter() {
    }

    @Override
    public void loadTheaters() {

        getData(true, true);

    }


    @Override
    public void loadComingSoon() {


    }

    @Override
    public void loadRefreshAndMore() {

        getData(false, false);
    }


    public void getData(boolean f1, boolean f2) {

        mList = new ArrayList<>();

        mList.clear();

        mList2 = new ArrayList<>();

        mModel.getTheaters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<MovieResponse<MovieSubjects>>(mView, f1, f2) {
                    @Override
                    public void onSucceed(MovieResponse<MovieSubjects> value) {
                        mList.add("正在热映");
                        mList.add(value.getSubjects());
                        loadComingSoon2();
                    }
                });


        //        Observable.merge(theaters, comingSoon)
        //                .subscribeOn(Schedulers.io())
        //                .observeOn(AndroidSchedulers.mainThread())
        //                .subscribe(new BaseObserver<MovieResponse<MovieSubjects>>(mView) {
        //                    @Override
        //                    public void onSucceed(MovieResponse<MovieSubjects> value) {
        //
        //
        //                        mList2.add(value);
        //
        //
        //                    }
        //
        //                    @Override
        //                    public void onComplete() {
        //                        super.onComplete();
        //
        //                        LogUtil.d("");
        //                    }
        //                });

        //        Observable<MovieResponse<MovieSubjects>> theaters = mModel.getTheaters().subscribeOn(Schedulers.io());
        //        Observable<MovieResponse<MovieSubjects>> comingSoon = mModel.getComingSoon().subscribeOn(Schedulers.io());
        //
        //        Observable.zip(theaters, comingSoon, new BiFunction<MovieResponse<MovieSubjects>, MovieResponse<MovieSubjects>, List<Object>>() {
        //            @Override
        //            public List<Object> apply(MovieResponse<MovieSubjects> movie1, MovieResponse<MovieSubjects> movie2) throws Exception {
        //                List<MovieSubjects> subjects = movie1.getSubjects();
        //                List<MovieSubjects> subjects1 = movie2.getSubjects();
        //                List<Object> list = new ArrayList<>();
        //                list.add("热门上映");
        //                list.add(subjects);
        //                list.add("即将上映");
        //                list.add(subjects1);
        //                return list;
        //            }
        //        }).observeOn(AndroidSchedulers.mainThread())
        //                .subscribe(new Consumer<List<Object>>() {
        //                    @Override
        //                    public void accept(List<Object> movieResponses) throws Exception {
        //
        //
        //                        mView.showAllData(movieResponses);
        //                    }
        //                }, new Consumer<Throwable>() {
        //                    @Override
        //                    public void accept(Throwable throwable) throws Exception {
        //                        LogUtil.d("失败" + throwable);
        ////                       mNormalView.setRefreshing(false);
        //                    }
        //                });


    }

    private void loadComingSoon2() {
        mModel.getComingSoon()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MovieResponse<MovieSubjects>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieResponse<MovieSubjects> value) {
                        List<MovieSubjects> subjects = value.getSubjects();
                        mView.showComingSoon(subjects);
                        mList.add("即将上映");
                        mList.add(subjects);

                        mView.showAllData(mList);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {


                    }
                });
    }
}
