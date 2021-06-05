package com.example.githubtrending.Base;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements View.OnClickListener{

    public P mPresenter;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);

        setContentView(getContentViewId());
        mPresenter = getPresenter();
        mPresenter.bindView(this);
        initView();
        initListener();
        initData();
    }

    protected abstract void initData();

    public abstract void initView();

    public abstract void initListener();

    public abstract int getContentViewId();

    public abstract P getPresenter();

    @Override
    protected void onDestroy(){
        super.onDestroy();
        destroy();
    }

    public abstract void destroy();
}
