package com.example.myrepository.mvp.base.mvp;

public interface IPresenter<T extends BaseView> {


    void attachView(T view);                            //注入View
    boolean isAttachView();                             //判断是否注入View
    void detachView();                                  //解除View
    //void addRxSubscribe(Disposable disposable);         //管理Rx的订阅事件
    void subscribeEvent();                              //订阅事件
    boolean getNightStyleState();                       //得到夜间模式的状态
    boolean getNoImgStyleState();                       //得到无图模式的状态
    boolean getAutoCacheState();                        //得到自动缓存模式的状态


}
