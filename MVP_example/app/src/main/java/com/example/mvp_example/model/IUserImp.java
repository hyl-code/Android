package com.example.mvp_example.model;

import com.example.mvp_example.bean.User;

public class IUserImp implements IUserModel{
    @Override
    public void login(final String userName, final String userPassword, final OnLoginListener onLoginListener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }

                if ("jack".equals(userName) && "123".equals(userPassword)){
                    User mUser = new User();
                    mUser.setUserName(userName);
                    mUser.setUserPassword(userPassword);

                    onLoginListener.loginSuccess(mUser);
                }else{
                    onLoginListener.loginFailed();
                }
            }
        }).start();
    }
}
