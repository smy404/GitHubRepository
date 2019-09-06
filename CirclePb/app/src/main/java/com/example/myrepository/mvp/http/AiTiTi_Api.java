package com.example.myrepository.mvp.http;

import com.example.myrepository.mvp.model.entity.aititi.AiTiTiResult;
import com.example.myrepository.mvp.model.entity.aititi.Banner;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AiTiTi_Api {

    @GET("app/v1.0/show_img/2")
    Call<AiTiTiResult<Banner>> getSplashAd();

}
