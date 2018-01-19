package com.example.vitaliy.news.data.rest;

import com.example.vitaliy.news.data.model.NewsModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by v_shevchyk on 15.01.18.
 */

public interface ApiInterface {
    @GET("/v2/top-headlines")
    Call<NewsModel> getTopNews(@Query("country") String country, @Query("apiKey") String apiKey);

    @GET("v2/everything")
    Call<NewsModel> getAllNews(@Query("q") String q, @Query("apiKey") String apiKey);

    @GET("/v2/top-headlines")
    Call<NewsModel> getTopNewsFiltered(@Query("country") String country, @Query("category") String category, @Query("apiKey") String apiKey);
}
