package com.home.quhong.quhong.TV.network.api;



import com.home.quhong.quhong.TV.entity.LiveIndex;
import com.home.quhong.quhong.TV.entity.Result;
import com.home.quhong.quhong.TV.entity.home.Synthesis;

import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Imitation by Abybxc on 16/8/4 12:03
 * weixin:aserbao
 * <p/>
 * 首页直播模块数据请求
 * <p/>
 * 备用API
 * http://bilibili-service.daoapp.io/appindex
 */
public interface LiveService
{
    /*@GET("AppIndex/home?_device=android&_hwid=51e96f5f2f54d5f9&_ulv=10000&access_key=563d6046f06289cbdcb472601ce5a761&appkey=c1b107428d337928&build=410000&platform=android&scale=xxhdpi&sign=fbdcfe141853f7e2c84c4d401f6a8758")
    Observable<Result<LiveIndex>> getLiveIndex();*/

    @GET("v1/home2/?site=home&language=us&channel=11013_3001_100")
    Observable<Synthesis> getSymthesHomeIndex();
    @GET("v1/home2/?site=movie&language=us&channel=11013_3001_100")
    Observable<Synthesis> getSymthesMovieIndex();
    @GET("v1/home2/?site=kd&language=us&channel=11013_3001_100")
    Observable<Synthesis> getSymthesKdIndex();
    @GET("v1/home2/?site=ws&language=us&channel=11013_3001_100")
    Observable<Synthesis> getSymthesWsIndex();

}
