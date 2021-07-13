package com.example.mvp_example.presenter;

import android.os.Handler;

import com.example.mvp_example.View.IUserView;
import com.example.mvp_example.bean.User;
import com.example.mvp_example.model.IUserImp;
import com.example.mvp_example.model.IUserModel;
import com.example.mvp_example.model.OnLoginListener;

public class UserLoginPresenter {

    private final IUserModel iUserModel;
    private final IUserView iUserView;
    private final Handler mHandler = new Handler();

    public UserLoginPresenter(IUserView iUserView){
        this.iUserView = iUserView;
        iUserModel = new IUserImp();
    }

    public void doLogin(){
        iUserView.showProgressDialog();

        iUserModel.login(iUserView.getUserName(), iUserView.getUserPassword(), new OnLoginListener() {
            @Override
            public void loginSuccess(final User mUser) {
                mHandler.post(new Runnable(){
                    @Override
                    public void run(){
                        iUserView.gotoMainView(mUser);
                        iUserView.hideProgressDialog();
                    }
                });
            }

            @Override
            public void loginFailed() {
                mHandler.post(new Runnable(){
                    @Override
                    public void run(){
                        iUserView.showErrorMessage();
                        iUserView.hideProgressDialog();
                    }
                });
            }
        });
    }

    public void doReset(){
        iUserView.clearUserName();
        iUserView.clearPassword();
    }
}
