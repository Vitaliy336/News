package com.example.vitaliy.news.ui.Model;

import android.util.Log;

import com.example.vitaliy.news.ui.Model.newsModel.NewsModel;
import com.example.vitaliy.news.ui.Model.rest.ApiClient;
import com.example.vitaliy.news.ui.Model.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by v_shevchyk on 15.01.18.
 */

public class NewsData {
    private static final String API_KEY = "8ba6904683434f37a16c07a1e0cde166";
    private List<NewsModel> news = new ArrayList<>();

    public List<NewsModel> NewsDataFromApi(){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<List<NewsModel>> call = apiInterface.getTopNews(API_KEY);
        call.enqueue(new Callback<List<NewsModel>>() {
            @Override
            public void onResponse(Call<List<NewsModel>> call, Response<List<NewsModel>> response) {
                Log.e("TAg", "ok");
                news.addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<NewsModel>> call, Throwable t) {
                Log.e("TAG", t.toString());
            }
        });

        return news;
    }
}
