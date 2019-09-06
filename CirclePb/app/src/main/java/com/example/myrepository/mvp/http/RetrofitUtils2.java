package com.example.myrepository.mvp.http;

import com.example.myrepository.Constant;
import com.example.myrepository.R;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils2 {

    private static HashMap<String, Retrofit> mRetrofitMap = new HashMap<>();
    private Retrofit mRetrofit;
    private static RetrofitUtils2 mRetrofitUtils2;


    private RetrofitUtils2() {

        mRetrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL_AiTiTi)
                .client(OkHttpUtils.getInstance().provideOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static RetrofitUtils2 getInstance() {
        if (mRetrofitUtils2 == null) {
            synchronized (RetrofitUtils2.class) {
                if (mRetrofitUtils2 == null) {
                    mRetrofitUtils2 = new RetrofitUtils2();
                }
            }
        }
        return mRetrofitUtils2;
    }


    public <T> T createApi(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }

}
