package com.rock.software.torrent.Activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.rock.software.torrent.iView.IBaseView;
import com.rock.software.torrent.presenter.BasePresenter;


public abstract class ToolBarActivity<T extends BasePresenter> extends BaseActivity implements IBaseView {
    protected ActionBar actionBar;
    protected T presenter;
    protected boolean isToolBarHiding = false;

    protected Toolbar toolbar;
    protected AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolBar();

    }



    protected boolean canBack(){
        return false;
    }

    protected boolean isHasBack(){
        return true;

    }

    protected void initToolBar(){
        if(isHasBack()) {
            setSupportActionBar(toolbar);
            actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(canBack());
                actionBar.setTitle(" ");
            }
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    @Override
    public void showProgressBar(String msg) {

    }

    @Override
    public void hideProgressBar() {
        dialog.dismiss();
    }





    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
