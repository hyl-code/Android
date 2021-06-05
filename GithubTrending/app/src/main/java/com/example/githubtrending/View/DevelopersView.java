package com.example.githubtrending.View;

import com.example.githubtrending.ListBean;

import java.util.List;

public interface DevelopersView extends View{
    void success(List<ListBean> listBeans);
    void onError(String result);
}
