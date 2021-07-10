package com.example.githubtrending.Model;

import android.os.Environment;

import androidx.annotation.NonNull;

import com.example.githubtrending.API;
import com.example.githubtrending.Base.BaseModel;
import com.example.githubtrending.Bean;
import com.example.githubtrending.Presenter.MainPresenter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import ren.yale.android.retrofitcachelib.RetrofitCache;
import okhttp3.Cache;
import ren.yale.android.retrofitcachelib.intercept.CacheForceInterceptorNoNet;
import ren.yale.android.retrofitcachelib.intercept.CacheInterceptorOnNet;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.githubtrending.MainActivity.BaseURL;

public class MainModel extends BaseModel<MainPresenter> implements API.M{
    public List<Bean.Items> mList = new ArrayList<>();

    public MainModel(MainPresenter mPresenter){
        super(mPresenter);
    }

    @Override
    public void request(){
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        int cacheSize = 100 * 1024 * 1024;
        File cacheDirectory = new File(Environment.getExternalStorageDirectory(), "httpcache");
        Cache cache = new Cache(cacheDirectory,cacheSize);
        OkHttpClient client = clientBuilder.cache(cache).build();
        clientBuilder.addInterceptor(new CacheForceInterceptorNoNet());
        clientBuilder.addNetworkInterceptor(new CacheInterceptorOnNet());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        RetrofitCache.getInstance().addRetrofit(retrofit);

        API api = retrofit.create(API.class);
        api.getList("java", "weekly").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bean<Bean.Items>>(){
                    @Override
                    public void onSubscribe(@NonNull Disposable d){

                    }

                    public void onNext(@NonNull Bean<Bean.Items> itemsBean){
                        for(int i = 0; i < itemsBean.getItems().size(); i++){
                            mList.add(itemsBean.getItems().get(i));
                        }
                        mPresenter.onSuccess(mList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e){
                        mPresenter.Fail();
                    }

                    @Override
                    public void onComplete(){
                        mPresenter.setData(mList);
                    }
                });
    }
}
