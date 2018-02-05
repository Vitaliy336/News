package com.example.vitaliy.news.pack.data.rest;

import com.example.vitaliy.news.pack.data.model.news.NewsResponse;
import com.example.vitaliy.news.pack.data.model.source.SourceResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by v_shevchyk on 15.01.18.
 */

public interface ApiInterface {

    @GET("/v2/top-headlines")
    Call<NewsResponse> getHotNews(@QueryMap Map<String, String> map);

    @GET("/v2/everything")
    Call<NewsResponse> getEverything(@QueryMap Map<String, String> map);

    @GET("/v2/sources")
    Call<SourceResponse> getSources(@QueryMap Map<String, String> map);
}
