package com.rock.software.torrent.presenter;

import android.content.Context;

import com.rock.software.torrent.iView.IBaseView;


public abstract class BasePresenter<T extends IBaseView> {

    protected Context context;
    protected T iView;


    public BasePresenter(Context context, T iView) {
        this.context = context;
        this.iView = iView;
    }

    public void init(){
        iView.initView();
    }


}
