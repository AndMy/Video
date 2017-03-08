package com.home.quhong.quhong.TV.network.api;

import com.home.quhong.quhong.TV.entity.home.HomeVideoDetail;
import com.home.quhong.quhong.TV.entity.home.Synthesis;
import com.home.quhong.quhong.TV.entity.video.VideoDetail;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by aserbao on 2017/3/3.
 * e_mail:abybxc@163.com
 * weixin:aserbao
 */

public interface VideoService {
    @GET("v1/videos/?start=0&limit=12&country=us&s=0")
    Observable<VideoDetail> getVideoDetail();
}
