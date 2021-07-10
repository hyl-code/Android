package com.example.githubtrending.View;

import android.view.View;

import com.example.githubtrending.Bean;

import java.util.List;

public interface DevelopersView{
    void success(List<Bean> beans);
    void onError(String result);
}
