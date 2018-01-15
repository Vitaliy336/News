package com.example.vitaliy.news.ui.Model.rest;

import com.example.vitaliy.news.ui.Model.newsModel.NewsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by v_shevchyk on 15.01.18.
 */

public interface ApiInterface {
    @GET("/v2/top-headlines?language=en")
    Call<List<NewsModel>> getTopNews(@Query("apiKey") String apiKey);

    @GET("/v2/everything")
    Call<NewsModel> getAllNews(@Query("apiKey") String apiKey);
}