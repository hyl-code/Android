package com.example.githubtrending;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.githubtrending.Base.BaseActivity;
import com.example.githubtrending.Presenter.MainPresenter;

import java.util.List;

public class MainActivity extends BaseActivity<MainPresenter> {

    private final int star = 0;
    private final int name = 1;
    public RecyclerView mRecyclerView;
    private List<ListBean> mData1;
    private List<ListBean> mData2;
    //private SwipeRefreshLayout refreshLayout;
    private Button mRetry;
    private int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = getPresenter();
        mPresenter.bindView(this);
        initListener();
        initView();
        initData();
    }

    @Override
    public void initView(){
        mRecyclerView = findViewById(R.id.RecyclerView);
        mRetry = findViewById(R.id.retry);
    }

    @Override
    public void initListener(){
        if (flag == 1)
            mRetry.setOnClickListener(this);
    }

    @Override
    public void initData(){

    }

    @Override
    public int getContentViewId(){
        switch (flag){
            case 0 :
                return R.layout.activity_main;
            default:
                return R.layout.error;
        }
    }

    @Override
    public MainPresenter getPresenter(){
        return new MainPresenter();
    }

    @Override
    public void destroy(){

    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.retry:
                flag = 0;
                break;
        }
    }

    public void setData(List<ListBean> list){

    }
}