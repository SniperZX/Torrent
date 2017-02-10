package com.rock.software.torrent.iView;

/**
 * Created by Administrator on 2016-04-19.
 */
public interface IBaseView {
    /**
     * 初始化
     */
    void initView();

    void showProgressBar(String msg);

    void hideProgressBar();

}
