package com.example.retrofit.retrofit;

import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit {
    private IBeanService service;

    /**
     * 获取Retrofit实例
     */

    public static Retrofit getRetrofit(){
        return new Retrofit();
    }

    private Retrofit(){
        retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("http://www.tngou.net/api/food/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(IBeanService.class);
    }

    /**
     * 获取IBeanService实例
     */

    public IBeanService getService(){
        return service;
    }
}
