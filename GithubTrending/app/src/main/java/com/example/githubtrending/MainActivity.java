package com.example.githubtrending;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListAdapter;

import com.example.githubtrending.Base.BaseActivity;
import com.example.githubtrending.Presenter.MainPresenter;
import com.example.githubtrending.View.ListViewAdapter;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.List;
import java.util.concurrent.TimeUnit;

import ren.yale.android.retrofitcachelib.RetrofitCache;

public class MainActivity extends BaseActivity<MainPresenter> implements API.V {

    public RecyclerView mRecyclerView;
    public static final String BaseURL = "https://trendings.herokuapp.com/";
    private final MainPresenter mPresenter = new MainPresenter();
    private ListViewAdapter mListViewAdapter;
    private List<Bean.Items> mList;
    private SwipeRefreshLayout refresh;
    private Button mRetry;
    private int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter.bindView(this);
        initListener();
        initView();
        initData();
        Fresco.initialize(this);
        RetrofitCache.getInstance().init(this).setDefaultTimeUnit(TimeUnit.HOURS).setDefaultTime(2);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void initView(){
        mRecyclerView = findViewById(R.id.recyclerview);
        mRetry = findViewById(R.id.retry);
        refresh = findViewById(R.id.refresh);
        refresh.setRefreshing(true);
        refresh.setColorSchemeColors(R.color.gray,R.color.purple_200,R.color.gray);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshList();
            }
        });
    }

    @Override
    public void initListener(){
        if (flag == 1)
            mRetry.setOnClickListener(this);
    }

    @Override
    public void initData(){
        refreshList();
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

    @Override
    public void setData(List<Bean.Items> list){
        mList = list;
        ListViewAdapter mListViewAdapter = new ListViewAdapter(mList);
        mRecyclerView.setAdapter(mListViewAdapter);
        refresh.setRefreshing(false);
    }

    @Override
    public void refreshList() {
        mPresenter.refreshList();
    }

    @Override
    public void onSuccess(List<Bean.Items> list){
        setContentView(R.layout.activity_main);
        initView();
        setData(list);
    }

    @Override
    public void Fail(){
        setContentView(R.layout.error);
        mRetry = findViewById(R.id.retry);
        mRetry.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                refreshList();
            }
        });
    }
}