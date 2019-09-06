package com.example.myrepository.mvp.http;

import com.example.myrepository.mvp.model.entity.Articles;
import com.example.myrepository.mvp.model.entity.BannerData;
import com.example.myrepository.mvp.model.entity.BaseResponse;
import com.example.myrepository.mvp.model.entity.MovieResponse;
import com.example.myrepository.mvp.model.entity.MovieSubjects;

import java.util.List;

import io.reactivex.Observable;

public interface NetworkHelper {

    /**
     * home
     */
    Observable<BaseResponse<List<BannerData>>> getBannerData();         //获取轮播图的数据

    Observable<BaseResponse<Articles>> getArticles(int pageNum);        //获取首页文章


    Observable<MovieResponse<MovieSubjects>> getTheaters();         // 正在热映

    Observable<MovieResponse<MovieSubjects>> getComingSoon();       // 即将上映

}
