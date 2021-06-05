package com.example.githubtrending.Model;

import com.example.githubtrending.Base.BaseModel;
import com.example.githubtrending.ListBean;
import com.example.githubtrending.Presenter.MainPresenter;

import java.util.List;

public class MainModel extends BaseModel<MainPresenter> {
    public List<ListBean> mListBeans;

    public MainModel(MainPresenter mPresenter){
        super(mPresenter);
    }


}
