package com.home.quhong.quhong.TV.network.api;

import com.home.quhong.quhong.TV.entity.filtrate.Filtrate;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by aserbao on 2017/3/23.
 */

public interface TestFiltrateService {
    @GET("search?classes=drama&category=romance&country=KR&order=hot&language=&start=0&end=10&language=IN")
    Observable<Filtrate> getTestFiltrateDetail(@Query("classes") String classes,
                                               @Query("category") String category,
                                               @Query("country") String country,
                                               @Query("order") String order,
                                               @Query("language") String language
    );
}
