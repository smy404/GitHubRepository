package com.example.myrepository.mvp.http;

import com.example.myrepository.utils.LogUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

public class OkHttpUtils {
    private static OkHttpUtils mOkHttpUtils;

    private OkHttpUtils() {


    }

    public static OkHttpUtils getInstance() {
        if (mOkHttpUtils == null) {
            synchronized (RetrofitUtils.class) {
                if (mOkHttpUtils == null) {
                    mOkHttpUtils = new OkHttpUtils();
                }
            }
        }
        return mOkHttpUtils;
    }

    public OkHttpClient provideOkHttpClient() {

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)//设置超时
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true);//错误重连


        builder.addNetworkInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LogUtil.d("打印Logo：" + message);
            }
        }));

        return builder.build();
    }
}
