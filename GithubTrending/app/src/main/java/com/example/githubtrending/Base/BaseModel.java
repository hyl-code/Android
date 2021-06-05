package com.example.githubtrending.Base;

public class BaseModel<P extends BasePresenter> {

    public P mPresenter;

    public BaseModel(P mPresenter){
        this.mPresenter = mPresenter;
    }
}
