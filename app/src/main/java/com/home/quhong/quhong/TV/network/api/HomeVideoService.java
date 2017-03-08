package com.home.quhong.quhong.TV.network.api;

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
//    @GET("/v1/info/?dramaId=58b38ab8488255bd7d3ea11a&language=id")

    @GET("/v1/info/?dramaId=58b38ab8488255bd7d3ea11a&language=id")
    Observable<HomeVideoDetail> getHomeVideoDetail(@Query("dramaId") String id);
}
