package com.example.githubtrending.Presenter;

import com.example.githubtrending.Base.BasePresenter;
import com.example.githubtrending.ListBean;
import com.example.githubtrending.MainActivity;
import com.example.githubtrending.Model.MainModel;

import java.util.List;

public class MainPresenter extends BasePresenter<MainActivity, MainModel> {

    public List<ListBean> mListBeans;

    @Override
    public MainModel getModelInstance(){
        return new MainModel(this);
    }

}
