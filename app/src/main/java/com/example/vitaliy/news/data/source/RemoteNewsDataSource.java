package com.example.vitaliy.news.data.source;

import android.text.TextUtils;

import com.example.vitaliy.news.data.newsModel.NewsModel;
import com.example.vitaliy.news.data.rest.ApiClient;
import com.example.vitaliy.news.data.rest.ApiInterface;
import com.example.vitaliy.news.data.sourceModel.SourceModel;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by v_shevchyk on 15.01.18.
 */

public class RemoteNewsDataSource implements NewsDataSource {
    private final static String API_KEY = "8ba6904683434f37a16c07a1e0cde166";
    private final ApiInterface apiInterface;

    public RemoteNewsDataSource() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    @Override
    public void getHotNews(final getListCallback callback, String category, String source) {
        Map<String, String> map = new HashMap<>();
        map.put("country", "us");
        if (!TextUtils.isEmpty(category)) {
            map.put("category", category);
        }
        if (!TextUtils.isEmpty(source)) {
            map.put("sources", source);
            map.remove("category");
            map.remove("country");
        }
        map.put("apiKey", API_KEY);
        if (callback != null) {
            apiInterface.getHotNews(map).enqueue(new Callback<NewsModel>() {
                @Override
                public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                    callback.onListReceived(response.body().getArticles());
                }

                @Override
                public void onFailure(Call<NewsModel> call, Throwable t) {

                }
            });
        }
    }

    @Override
    public void getEverything(final getListCallback callback, String query) {
        Map<String, String> map = new HashMap<>();
        map.put("q", query);
        map.put("apiKey", API_KEY);
        if (callback != null) {
            apiInterface.getEverything(map).enqueue(new Callback<NewsModel>() {
                @Override
                public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                    callback.onListReceived(response.body().getArticles());
                }

                @Override
                public void onFailure(Call<NewsModel> call, Throwable t) {

                }
            });
        }
    }

    @Override
    public void getSources(final getListCallback callback, String category) {
        Map<String, String> map = new HashMap<>();
        if (!TextUtils.isEmpty(category)) {
            map.put("category", category);
        }
        map.put("apiKey", API_KEY);
        if (callback != null) {
            apiInterface.getSources(map).enqueue(new Callback<SourceModel>() {
                @Override
                public void onResponse(Call<SourceModel> call, Response<SourceModel> response) {
                    callback.onListReceived(response.body().getSources());
                }

                @Override
                public void onFailure(Call<SourceModel> call, Throwable t) {

                }
            });
        }
    }
}
