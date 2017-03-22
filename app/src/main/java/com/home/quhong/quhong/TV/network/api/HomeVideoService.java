package com.home.quhong.quhong.TV.network.api;

import com.home.quhong.quhong.TV.entity.detail.VideoDetail;
import com.home.quhong.quhong.TV.entity.home.HomeVideoDetail;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

import static android.R.attr.id;

/**
 * Created by aserbao on 2017/3/8.
 * e_mail:abybxc@163.com
 * weixin:aserbao
 */

public interface HomeVideoService {
    @GET("/v1/vinfo?vid=58c8f8f61bad4863abd7def0&language=IN")
    Observable<VideoDetail> getHomeVideoDetail(@Query("dramaId") String id);

   /* @GET("v1/vinfo?vid=58b8d7b81bad481c92347df4&language=IN")
    Observable<VideoDetail> getHomeVideoDetail();*/
}
