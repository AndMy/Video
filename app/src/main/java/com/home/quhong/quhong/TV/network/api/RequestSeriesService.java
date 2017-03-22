package com.home.quhong.quhong.TV.network.api;

import com.home.quhong.quhong.TV.entity.detail.RequestSeries;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by aserbao on 2017/3/22.
 */

public interface RequestSeriesService {
    @GET("v1/vdown?type=yt&code=oDD3I0uOlqY")
    Observable<RequestSeries> getSeriesUrlAgain();
}
