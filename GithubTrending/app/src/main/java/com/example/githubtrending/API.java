package com.example.githubtrending;

import java.util.List;

import io.reactivex.Observable;
import ren.yale.android.retrofitcachelib.anno.Cache;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    public interface M {
        void request();
    }

    public interface V{
        void refreshList();
        void onSuccess(List<Bean.Items> list);
        void setData(List<Bean.Items> list);
        void Fail();
    }

    @Cache
    @GET("/repo")
    Observable<Bean<Bean.Items>> getList(@Query("lang")String language, @Query("since")String weekly);
}
