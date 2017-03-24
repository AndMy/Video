package com.home.quhong.quhong.TV.network;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.home.quhong.quhong.QuHongApp;
import com.home.quhong.quhong.TV.network.api.FloatButtonService;
import com.home.quhong.quhong.TV.network.api.HomeVideoService;
import com.home.quhong.quhong.TV.network.api.LiveService;
import com.home.quhong.quhong.TV.network.api.RequestSeriesService;
import com.home.quhong.quhong.TV.network.api.FiltrateService;
import com.home.quhong.quhong.TV.network.api.VideoService;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Imitation by Abybxc on 16/8/4 21:18
 * weixin:aserbao
 * <p/>
 * Retrofit帮助类
 */
public class RetrofitHelper
{

    private static OkHttpClient mOkHttpClient;


//    private static final String BASE_URL = "http://api.beemovieapp.com/";
    private static final String BASE_URL = "http://www.indiadsp.com:9998/";
    private static final String BASE_VIDEO_URL = "http://beemovie.cooshows.com/";
    private static final String BASE_SERIES_URL = "http://www.qmovies.tv:8080/";
    private static final String BASE_FLOAT_BUTTON_URL = "http://www.indiadsp.com:9998/";


    private static final String COMMON_UA_STR = "OhMyBiliBili Android Client/2.1 (weixin:aserbao )";

    static
    {
        initOkHttpClient();
    }

    /**
     * 获取视频Api
     * @return
     */

    public static LiveService getLiveApi()
    {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(LiveService.class);
    }
    public static HomeVideoService getHomeVideoApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERIES_URL)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return  retrofit.create(HomeVideoService.class);
    }
    /**
     * 获取VideoDetail
     */
    public static VideoService getVideoApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_VIDEO_URL)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(VideoService.class);
    }

    /**
     * 二次获取Video的播放地址
     */
    public static RequestSeriesService getRequestSeriesApi()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_SERIES_URL)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(RequestSeriesService.class);
    }

    public static FiltrateService getFiltrateApi()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_FLOAT_BUTTON_URL)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(FiltrateService.class);
    }
    public static FiltrateService getRefreshFiltrateApi()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_FLOAT_BUTTON_URL)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(FiltrateService.class);
    }


    /**
     * 初始化OKHttpClient
     * 设置缓存
     * 设置超时时间
     * 设置打印日志
     * 设置UA拦截器
     */
    private static void initOkHttpClient()
    {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (mOkHttpClient == null)
        {
            synchronized (RetrofitHelper.class)
            {
                if (mOkHttpClient == null)
                {
                    //设置Http缓存
                    Cache cache = new Cache(new File(QuHongApp.getInstance()
                            .getCacheDir(), "HttpCache"), 1024 * 1024 * 100);

                    mOkHttpClient = new OkHttpClient.Builder()
                            .cache(cache)
                            .addInterceptor(interceptor)
                            .addNetworkInterceptor(new StethoInterceptor())
                            .retryOnConnectionFailure(true)
                            .connectTimeout(30, TimeUnit.SECONDS)
                            .writeTimeout(20, TimeUnit.SECONDS)
                            .readTimeout(20, TimeUnit.SECONDS)
                            .addInterceptor(new UserAgentInterceptor())
                            .build();
                }
            }
        }
    }


    /**
     * 添加UA拦截器
     * 请求API文档需要加上UA
     */
    private static class UserAgentInterceptor implements Interceptor
    {

        @Override
        public Response intercept(Chain chain) throws IOException
        {

            Request originalRequest = chain.request();
            Request requestWithUserAgent = originalRequest.newBuilder()
                    .removeHeader("User-Agent")
                    .addHeader("User-Agent", COMMON_UA_STR)
                    .build();
            return chain.proceed(requestWithUserAgent);
        }
    }
}
