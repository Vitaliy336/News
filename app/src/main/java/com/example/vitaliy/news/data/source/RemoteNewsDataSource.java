package com.example.vitaliy.news.data.source;

import android.text.TextUtils;
import android.util.Log;

import com.example.vitaliy.news.data.model.news.NewsResponse;
import com.example.vitaliy.news.data.model.source.SourceResponse;
import com.example.vitaliy.news.data.rest.ApiClient;
import com.example.vitaliy.news.data.rest.ApiInterface;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
            apiInterface.getHotNews(map).enqueue(new Callback<NewsResponse>() {
                @Override
                public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                    callback.onListReceived(response.body().getArticles());
                }

                @Override
                public void onFailure(Call<NewsResponse> call, Throwable t) {
                    Log.e("REMOTE", "onFailure");
                    callback.onFailure();
                }
            });
        }
    }

    @Override
    public void getEverything(final getListCallback callback, String query) {
        Map<String, String> map = new HashMap<>();
        if (!TextUtils.isEmpty(query)) {
            map.put("q", query);
        }
        map.put("apiKey", API_KEY);
        if (callback != null) {
            apiInterface.getEverything(map).enqueue(new Callback<NewsResponse>() {
                @Override
                public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                    callback.onListReceived(response.body().getArticles());
                }

                @Override
                public void onFailure(Call<NewsResponse> call, Throwable t) {
                    callback.onFailure();
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
            apiInterface.getSources(map).enqueue(new Callback<SourceResponse>() {
                @Override
                public void onResponse(Call<SourceResponse> call, Response<SourceResponse> response) {
                    callback.onListReceived(response.body().getSources());
                }

                @Override
                public void onFailure(Call<SourceResponse> call, Throwable t) {
                    callback.onFailure();
                }
            });
        }
    }
}
