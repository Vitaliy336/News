package com.example.vitaliy.news.data.rest;

import com.example.vitaliy.news.data.model.NewsModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by v_shevchyk on 15.01.18.
 */

public interface ApiInterface {
    @GET("/v2/top-headlines")
    Call<NewsModel> getTopNews(@Query("country") String country, @Query("apiKey") String apiKey);

    @GET("v2/everything?q=bitcoin")
    Call<NewsModel> getAllNews(@Query("apiKey") String apiKey);
}
