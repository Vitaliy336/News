package com.example.vitaliy.news.pack.ui.searchNews;

import android.text.TextUtils;
import android.util.Log;

import com.example.vitaliy.news.pack.data.model.news.Article;
import com.example.vitaliy.news.pack.data.source.NewsDataSource;
import com.example.vitaliy.news.pack.data.source.RemoteNewsDataSource;

import java.util.List;

/**
 * Created by v_shevchyk on 12.01.18.
 */

public class SearchNewsPresenter implements SearchNewsContract.IAllNewsPresenter {
    private String query = null;
    private SearchNewsContract.IAllNewsView view;
    private RemoteNewsDataSource newsDataSource;

    public SearchNewsPresenter(RemoteNewsDataSource newsDataSource) {
        this.newsDataSource = newsDataSource;
    }

    @Override
    public void attachView(SearchNewsContract.IAllNewsView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void start() {
        if(!TextUtils.isEmpty(query)){
            view.hideMessage();
            prepareNews();
        }
        else {
            view.showMessage();
        }
    }

    @Override
    public void prepareNews() {
        newsDataSource.getEverything(new NewsDataSource.getListCallback() {
            @Override
            public void onListReceived(List<?> list) {
                view.displayNews((List<Article>) list);
            }

            @Override
            public void onFailure() {
                Log.e("Failure", "fail");
            }
        }, query);
    }


    @Override
    public void goTofullNews(String url) {
        view.showFullNews(url);
    }

    @Override
    public void getSearchQuery(String query) {
        this.query = query;
    }

}