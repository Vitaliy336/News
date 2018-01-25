package com.example.vitaliy.news.data.rest;

import com.example.vitaliy.news.data.newsModel.NewsModel;
import com.example.vitaliy.news.data.sourceModel.SourceModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by v_shevchyk on 15.01.18.
 */

public interface ApiInterface {

    @GET("/v2/top-headlines")
    Call<NewsModel> getHotNews(@QueryMap Map<String, String> map);

    @GET("/v2/everything")
    Call<NewsModel> getEverything(@QueryMap Map<String, String> map);

    @GET("/v2/sources")
    Call<SourceModel> getSources(@QueryMap Map<String, String> map);
}
