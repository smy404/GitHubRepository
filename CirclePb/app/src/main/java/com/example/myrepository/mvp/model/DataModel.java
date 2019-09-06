package com.example.myrepository.mvp.model;

import com.example.myrepository.mvp.http.NetworkHelper;
import com.example.myrepository.mvp.http.Api;
import com.example.myrepository.mvp.http.RetrofitUtils;
import com.example.myrepository.mvp.model.entity.Articles;
import com.example.myrepository.mvp.model.entity.BannerData;
import com.example.myrepository.mvp.model.entity.BaseResponse;
import com.example.myrepository.mvp.model.entity.MovieResponse;
import com.example.myrepository.mvp.model.entity.MovieSubjects;

import java.util.List;

import io.reactivex.Observable;

public class DataModel implements NetworkHelper {


    private final Api mApi;

    public DataModel() {

        mApi = RetrofitUtils.getInstance().provideApi();

    }

    @Override
    public Observable<BaseResponse<List<BannerData>>> getBannerData() {
        return mApi.getBannerData();
    }

    @Override
    public Observable<BaseResponse<Articles>> getArticles(int pageNum) {
        return mApi.getArticles(pageNum);
    }

    @Override
    public Observable<MovieResponse<MovieSubjects>> getTheaters() {
        return mApi.getTheaters();
    }

    @Override
    public Observable<MovieResponse<MovieSubjects>> getComingSoon() {
        return mApi.getComingSoon();
    }
}
