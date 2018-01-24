package com.example.vitaliy.news.ui.allnews;

import android.util.Log;

import com.example.vitaliy.news.data.newsModel.Article;
import com.example.vitaliy.news.data.source.NewsDataSource;
import com.example.vitaliy.news.data.source.RemoteNewsDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by v_shevchyk on 12.01.18.
 */

public class AllNewsPresenter implements AllNewsContract.IAllNewsPresenter {
    private String query = "android";
    private AllNewsContract.IAllNewsView view;
    private RemoteNewsDataSource newsDataSource;

    public AllNewsPresenter(RemoteNewsDataSource newsDataSource) {
        this.newsDataSource = newsDataSource;
    }

    @Override
    public void attachView(AllNewsContract.IAllNewsView view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    @Override
    public void onTextClickListener() {
        view.showToastMessage();
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
    public void prepareCategories() {
        List<String> categories = new ArrayList<>();
        categories.add("Sport");
        categories.add("Politic");
        categories.add("Woirld");
        categories.add("BBC");
        categories.add("Others");
        view.displayCategories(categories);
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