package com.example.retrofit.retrofit;

import com.example.retrofit.bean.Bean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IBeanService {

    @GET("show")
        Call<Bean> getMenuById(@Query("id") String id);

}
