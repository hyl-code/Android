package com.example.mvp_example.model;

import com.example.mvp_example.bean.User;

public interface OnLoginListener {

    // 登录的回调方法
    void loginSuccess(User mUser);

    void loginFailed();
}
