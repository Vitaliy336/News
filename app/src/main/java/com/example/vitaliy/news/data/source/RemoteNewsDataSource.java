package com.example.vitaliy.news.data.source;

import android.util.Log;

import com.example.vitaliy.news.data.newsModel.NewsModel;
import com.example.vitaliy.news.data.rest.ApiClient;
import com.example.vitaliy.news.data.rest.ApiInterface;
import com.example.vitaliy.news.data.sourceModel.Source;
import com.example.vitaliy.news.data.sourceModel.SourceModel;

import java.util.List;

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
    public void topNewsWithSource(final getListCallback callback, final String source) {
        if(callback != null){
            apiInterface.getNewsWithSource(source, API_KEY).enqueue(new Callback<NewsModel>() {
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
                    Log.e("Error", t.toString());
                }
            });
        }

    }

    @Override
    public void allDataFromApi(final getListCallback callback) {
        if(callback != null){
            apiInterface.getAllNews("bitcoin", API_KEY).enqueue(new Callback<NewsModel>() {
                @Override
                public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                    callback.onListReceived(response.body().getArticles());
                }

                @Override
                public void onFailure(Call<NewsModel> call, Throwable t) {
                    callback.onFailure();
                    Log.e("Error", t.toString());
                }
            });
        }
    }

    @Override
    public void sourcesDataFromApi(final getListCallback callback) {
        if(callback!=null){
            apiInterface.getSources(API_KEY).enqueue(new Callback<SourceModel>() {
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


    @Override
    public void hotNewsWithFilter(final getListCallback callback, String category) {
        if(callback != null){
            apiInterface.getTopNewsFiltered("us", category, API_KEY).enqueue(new Callback<NewsModel>() {
                @Override
                public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                    callback.onListReceived(response.body().getArticles());
                }

                @Override
                public void onFailure(Call<NewsModel> call, Throwable t) {
                    Log.e("Error", t.toString());
                }
            });
        }
    }

    @Override
    public void allNewsWithSearchQuery(final getListCallback callback, String query) {
        if(callback != null){
            apiInterface.getAllNews(query, API_KEY).enqueue(new Callback<NewsModel>() {
                @Override
                public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                    callback.onListReceived(response.body().getArticles());
                }

                @Override
                public void onFailure(Call<NewsModel> call, Throwable t) {
                    Log.e("Error", t.toString());
                }
            });
        }
    }

    @Override
    public void allSourcesDataWithFilter(final getListCallback callback, String category) {
        if(callback != null){
            apiInterface.getSourcesWithCategory(category, API_KEY).enqueue(new Callback<SourceModel>() {
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
