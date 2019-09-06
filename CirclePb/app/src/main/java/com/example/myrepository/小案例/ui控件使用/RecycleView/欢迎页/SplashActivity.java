package com.example.myrepository.小案例.ui控件使用.RecycleView.欢迎页;

import android.widget.ImageView;

import com.example.myrepository.R;
import com.example.myrepository.mvp.base.BaseActivity;
import com.example.myrepository.mvp.http.AiTiTi_Api;
import com.example.myrepository.mvp.http.RetrofitUtils2;
import com.example.myrepository.mvp.model.entity.aititi.AiTiTiResult;
import com.example.myrepository.mvp.model.entity.aititi.Banner;
import com.example.myrepository.utils.LogUtil;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends BaseActivity {

    @BindView(R.id.iv_ad)
    ImageView mIvAd;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void inject() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

        Call<AiTiTiResult<Banner>> splashAd = RetrofitUtils2.getInstance().createApi(AiTiTi_Api.class).getSplashAd();

        splashAd.enqueue(new Callback<AiTiTiResult<Banner>>() {
            @Override
            public void onResponse(Call<AiTiTiResult<Banner>> call, Response<AiTiTiResult<Banner>> response) {

                AiTiTiResult<Banner> list = response.body();
            }

            @Override
            public void onFailure(Call<AiTiTiResult<Banner>> call, Throwable t) {
                LogUtil.d(t.getMessage());
            }
        });


    }
}
