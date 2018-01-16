package com.example.vitaliy.news.data.source;

import com.example.vitaliy.news.data.model.NewsModel;
import com.example.vitaliy.news.data.rest.ApiClient;
import com.example.vitaliy.news.data.rest.ApiInterface;

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
    public void topDataFromApi(final getListCallback callback) {
        if (callback != null) {
            apiInterface.getTopNews("us", API_KEY).enqueue(new Callback<NewsModel>() {
                @Override
                public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                    callback.onListReceived(response.body().getArticles());
                }

                @Override
                public void onFailure(Call<NewsModel> call, Throwable t) {
                    callback.onFailure();
                }
            });
        }

    }

    @Override
    public void AllDataFromApi(final getListCallback callback) {
        if(callback != null){
            apiInterface.getAllNews("bitcoin", API_KEY).enqueue(new Callback<NewsModel>() {
                @Override
                public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                    callback.onListReceived(response.body().getArticles());
                }

                @Override
                public void onFailure(Call<NewsModel> call, Throwable t) {
                    callback.onFailure();
                }
            });
        }
    }
}
