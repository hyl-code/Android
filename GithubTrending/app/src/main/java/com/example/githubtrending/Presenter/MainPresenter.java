package com.example.githubtrending.Presenter;

import com.example.githubtrending.API;
import com.example.githubtrending.Base.BasePresenter;
import com.example.githubtrending.Base.BaseModel;
import com.example.githubtrending.Bean;
import com.example.githubtrending.MainActivity;
import com.example.githubtrending.Model.MainModel;

import java.util.List;

public class MainPresenter extends BasePresenter<MainActivity, MainModel> implements API.V{

    public void bindView(MainActivity mView){
        this.mView = mView;
    }

    public void unBindView(){
        this.mView = null;
    }

    @Override
    public MainModel getModelInstance(){
        return new MainModel(this);
    }

    @Override
    public void refreshList(){
        mModel.request();
    }

    @Override
    public void onSuccess(List<Bean.Items> list){
        mView.onSuccess(list);
    }

    @Override
    public void setData(List list){
        mView.setData(list);
    }

    @Override
    public void Fail(){
        mView.Fail();
    }

}
