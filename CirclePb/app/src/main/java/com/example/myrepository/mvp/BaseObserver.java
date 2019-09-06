package com.example.myrepository.mvp;

import android.net.ParseException;

import com.example.myrepository.App;
import com.example.myrepository.R;
import com.example.myrepository.mvp.base.mvp.BaseView;
import com.example.myrepository.mvp.http.ApiException;
import com.example.myrepository.utils.LogUtil;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.UnknownHostException;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.ResourceObserver;
import retrofit2.HttpException;

public abstract class BaseObserver<T> extends ResourceObserver<T> {

    public static final String TAG_ERROR = "error";

    private BaseView mView;
    private boolean isShowErrorView = true;
    private boolean isShowLoading = true;
    private Disposable mDisposable;

    public BaseObserver() {
    }

    protected BaseObserver(BaseView view) {
        this(view, true, true);
    }

    protected BaseObserver(BaseView view, boolean isShowErrorView) {
        this(view, isShowErrorView, true);
    }

    protected BaseObserver(BaseView view, boolean isShowErrorView, boolean isShowLoading) {
        mView = view;
        this.isShowErrorView = isShowErrorView;
        this.isShowLoading = isShowLoading;
    }


    @Override
    protected void onStart() {
        if (isShowLoading) {
            mView.showLoading();
        }

    }

    @Override
    public void onNext(T value) {
        mView.showNormalView();

        onSucceed(value);
    }

    public abstract void onSucceed(T value);

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof UnknownHostException) {
            LogUtil.e(TAG_ERROR, "networkError：" + e.getMessage());
            networkError();
        } else if (e instanceof InterruptedException) {
            LogUtil.e(TAG_ERROR, "timeout：" + e.getMessage());
            timeoutError();
        } else if (e instanceof HttpException) {
            LogUtil.e(TAG_ERROR, "http错误：" + e.getMessage());
            httpError();
        } else if (e instanceof JsonParseException || e instanceof JSONException || e instanceof ParseException) {
            LogUtil.e(TAG_ERROR, "解析错误：" + e.getMessage());
            parseError();
        } else if (e instanceof ApiException) {
        }
    }


    @Override
    public void onComplete() {
        dispose();

    }


    /**
     * 未知错误
     */
    protected void unknown() {
        mView.showToast(App.getContext().getString(R.string.error_unknown));
        if (isShowErrorView) mView.showErrorView();
    }

    /**
     * 解析错误
     */
    protected void parseError() {
        mView.showToast(App.getContext().getString(R.string.error_parse));
        if (isShowErrorView) mView.showErrorView();
    }

    /**
     * http错误
     */
    protected void httpError() {
        mView.showToast(App.getContext().getString(R.string.error_http));
        if (isShowErrorView) {
            LogUtil.d(LogUtil.TAG, "errorView");
            mView.showErrorView();
        }
    }

    /**
     * 网络超时异常
     */
    protected void timeoutError() {
        mView.showToast(App.getContext().getString(R.string.error_timeout));
        if (isShowErrorView) mView.showErrorView();
    }

    /**
     * 网络不可用异常
     */
    protected void networkError() {
        mView.showToast(App.getContext().getString(R.string.error_network));
        if (isShowErrorView) mView.showErrorView();
    }

}
