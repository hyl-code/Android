package com.example.githubtrending.Base;

import com.example.githubtrending.MainActivity;
import com.example.githubtrending.Model.MainModel;

public abstract class BasePresenter<V extends BaseActivity,M extends BaseModel>{
    public V mView;
    public M mModel;

    public BasePresenter(){
        this.mModel = getModelInstance();
    }

    public void bindView(V mView) {
        this.mView = mView;
    }

    public void unBindView(){
        this.mView = null;
    }

    public abstract M getModelInstance();
}
